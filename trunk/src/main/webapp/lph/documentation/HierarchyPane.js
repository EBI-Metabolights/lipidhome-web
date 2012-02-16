/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The HierarchyPane is a Tree that represents the structure of the items in the documentation static resources.
 * It uses a dedicated servlet who provides the documentation static folder content (listing the content of the
 * static folders use to be denied by security reasons).
 *
*/
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
    mainFolder  : 'resources/static/documentation',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
            optionSelected: true
        });

        var rootNode = this.getRootNode();
        rootNode.set('path', "/");
        this.loadNodeContent(this.getRootNode());

        this.addListener("selectionchange", this._selectionChange, this);

        return this;
    },

    loadNodeContent: function(node){
        Ext.Ajax.request({
            url     : 'servlets/documentation',
            params  : {
                path    : node.get('path')
            },
            success : function(response,opts) {
                this._loadNodeContent(node, response); //.responseText.match(regx));
            },
            scope   : this
        });
    },

    _loadNodeContent: function(node, response){
        var res = Ext.decode(response.responseText);
        if(res.success){
            if(res.content.folders){
                Ext.Array.each(res.content.folders, function(folder){
                    var name = Ext.String.capitalize(folder.replace(/^(\d*_)/,"").replace(/_/g," "));
                    var newNode = node.appendChild({
                        text	: name,
                        path    : node.get('path') + folder + "/",
                        leaf    : false,
                        iconCls : name.toLowerCase().replace(/ /g,"-") + "-16"
                    });
                    this.loadNodeContent(newNode);
                }, this);
            }

            if(res.content.files){
                Ext.Array.each(res.content.files, function(file){
                    var name =  file.replace(/[.]html$/g,"").replace(/^(\d*_)/,"").replace(/_/g," ");
                    var path = this.mainFolder + node.get('path') + file;
                    var newNode = node.appendChild({
                        text	: Ext.String.capitalize(name),
                        path    : path,
                        leaf    : true,
                        iconCls : "document-16"
                    });
                }, this);
            }
        }
    },

    _selectionChange: function(tree, selection, opts){
        var sel = selection[0];
        if(sel.isLeaf()){
            this.fireEvent('optionSelected', sel.get('path'));
        }
    }
});