/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * This is a simple Container for the two elements of the NavPane; HierarchyPane and the SearchPane.
 *
*/

Ext.define('lph.browser.nav.NavPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    region		: 'west',
    split		: true,
    width		: 400,
    minWidth	: 300,
	maxWidth	: 600,
	frame		: false,
	border		: false,
	layout		: 'border',
    tries       : 3,
    timeToTry   : 1000, //in milliseconds
	
	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	itemClicked	: true
        });
        
        this.search = Ext.create('lph.browser.nav.SearchPane');
        this.add(this.search);
               
        var store = Ext.create('Ext.data.TreeStore', {
            fields  : [
                {name: 'itemId',  type : 'int'},
                {name: 'name',  type : 'string'},
                {name: 'type',  type : 'string', defaultValue: 'category'}
            ]
		});
			
        this.hierarchy = Ext.create('lph.browser.nav.HierarchyPane',{
        	store : store
        });
        this.add(this.hierarchy);

        this.bind();

		this.loadTree();
  
        return this;
    },

    bind: function(){
        this.search.addListener("itemSelected", Ext.bind(this.hierarchy.findPathTo, this.hierarchy));
    },

    /*
        This function retrieves the initial information to be shown in the hierarchyPane
    */
    loadTree: function(){
    	Ext.Ajax.request({
        	url : 'service/category/list',
        	success: this.processResponse,
            failure: this._loadTree,
        	scope : this
        })
    },

    _loadTree:function(){
        if( this.tries-- == 0 ){
            if(!Ext.isEmpty(this.messageBox)) this.messageBox.hide();
            alert("There is a problem connecting to the database. Please, try again later");
        }else{
            this.messageBox = Ext.MessageBox.show({
                msg             : 'There was a problem connecting to the database. Trying to connect again.',
                progressText    : 'Waiting to connect again...',
                width           : 300,
                wait            : true,
                waitConfig      : {interval:200},
                icon            : 'ext-mb-download', //custom class in msg-box.html
                animateTarget   : lph.viewport
            });
            setTimeout(Ext.bind(this.loadTree, this), this.timeToTry);
        }
    },

    /*
        This function processes and appends the initial information to be shown in the HierarchyPane
    */
    processResponse: function(response){
        if(!Ext.isEmpty(this.messageBox)) this.messageBox.hide();

    	var node = this.hierarchy.getStore().getRootNode( )
    	var res = Ext.decode(response.responseText);
    	
    	Ext.each(res.list, function(item){
    		node.appendChild({
    			itemId	: item.itemId,
    			text	: item.name,
    			expanded: true,
    			type	: item.type,
                iconCls : "category-identified"
    		});
    	}, this);
    }
});