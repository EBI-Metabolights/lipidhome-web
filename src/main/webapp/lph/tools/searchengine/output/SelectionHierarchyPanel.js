Ext.define('lph.tools.searchengine.output.SelectionHierarchyPanel', {
	/* Begin Definitions */
    extend	: 'Ext.tree.Panel',
   
    layout	    : 'fit',
    title	    : 'Filtering options',
    iconCls     : 'filter-16',
    border	    : false,
    margins	    : '0 5 0 5',
    frame	    : true,
    rootVisible	: false,
	useArrows	: true,
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
            filterchange : true
        });

        this._loadNode(this.getStore().getRootNode());

        this.bind();

        return this;
    },

    bind: function(){
        this.addListener('beforeitemexpand', this._loadNode, this);
        this.addListener('checkchange', this._checkChange, this)
    },

    /*
        This function retrieves the initial information to be shown in the hierarchyPane
    */
    _loadNode: function(node){
        if(node.hasChildNodes()) return;

        var url = "";
        var type = node.get("type");
        var leaf = false;

        var checked = node.get("checked");
        checked = (checked==null) ? true : checked;

        if(type == ""){
            url = 'service/category/list';
            type = "category";
            leaf = false;
        }else if(type == "category"){
            url = 'service/category/mainclasses?id=' + node.get("itemId");
            type = "mainClass";
            leaf = false;
        }else if(type == "mainClass"){
            url = 'service/mainclass/subclasses?id=' + node.get("itemId");
            type = "subClass";
            leaf = true;
        }else {
            return; //COMPLETED
        }
    	Ext.Ajax.request({
        	url : url,
        	success: Ext.bind(this.processResponse,this,[node, type, checked, leaf],true),
        	scope : this
        })
    },

    _checkChange: function(node, checked, opts){
        this.suspendEvents();
        node.cascadeBy(function(n){
            n.set('checked', checked);
        });
        this.resumeEvents();
        this.fireEvent('filterchange', node, checked, opts);
    },

    /*
        This function processes and appends the initial information to be shown in the HierarchyPane
    */
    processResponse: function(response, opts, node, type, checked, leaf){
    	var res = Ext.decode(response.responseText);

    	Ext.each(res.list, function(item){
    		node.appendChild({
    			itemId	: item.itemId,
    			text	: item.code,
                //qtitle  : item.code,
                qtip    : item.name,
    			type	: type,
                checked : checked,
                expanded: true,
                leaf    : leaf,
                iconCls : type + "-identified"
    		});
    	}, this);

        //Leaf here means that children are LEAF (not this node -> who is the parent)
        if(leaf){
            node.collapse();
        }
    }
});