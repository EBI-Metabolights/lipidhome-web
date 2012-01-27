Ext.define('lph.documentation.HierarchyPane', {
	/* Begin Definitions */
    extend	    : 'Ext.tree.Panel',

    region	    : 'west',
    layout	    : 'fit',
    title	    : 'Options',
    iconCls     : 'hierarchy-16',
    border	    : false,
    margins	    : '0 5 0 5',
    frame	    : true,
    rootVisible	: false,
	useArrows	: true,
    width       : 300,
    maxWidth    : 300,
    minWidth    : 200,
    collapsible : true,
    split		: true,
    mainFolder  : 'resources/static/documentation',    //Always ending with "/"

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
            optionSelected: true
        });

        this.loadNodeContent(this.getRootNode());

        this.addListener("selectionchange", this._selectionChange, this);

        return this;
    },

    loadNodeContent: function(node){
        var path = node.isRoot()?this.mainFolder:node.get('path');
        this._getNodeContent(node, path + "/");
    },

    _loadNodeContent: function(node, paths){
        Ext.Array.each(paths, function(path){
            var prop = this._getPathProperties(path);
            var newNode = node.appendChild({
                text	: prop.name.replace(/_/g," "),
                path    : prop.path,
                leaf    : prop.isFile,
                iconCls : prop.iconCls
		    });
            if(!prop.isFile){
                this.loadNodeContent(newNode);
            }
        }, this);
    },

    _getNodeContent: function(node, path){
        var regx =  new RegExp(path + "[a-zA-Z0-9_]+(\.html)?", "g");
        Ext.Ajax.request({
            url     : path,
            success : function(response,opts) {
                this._loadNodeContent(node, response.responseText.match(regx));
            },
            scope   : this
        });
    },

    _getPathProperties: function(path){
        var regx =  new RegExp("[a-zA-Z0-9_]+\.html$", "g");
        var names = path.match(regx);
        var isFile = (names!=null);
        var name = '';
        var iconCls = '';
        if(isFile){
            name = names[0].replace(/[.]html$/g,"");
            iconCls = "document-16";
        }else{
            var elems = path.split("\/");
            name = elems.length==0?'':elems[elems.length-1];
            iconCls = name.toLowerCase().replace(/_/g,"-") + "-16";
        }

        return {
            path    : path,
            isFile  : isFile,
            name    : Ext.String.capitalize(name),
            iconCls : iconCls
        };
    },

    _selectionChange: function(tree, selection, opts){
        var sel = selection[0];
        if(sel.isLeaf()){
            this.fireEvent('optionSelected', sel.get('path'));
        }
    }
});