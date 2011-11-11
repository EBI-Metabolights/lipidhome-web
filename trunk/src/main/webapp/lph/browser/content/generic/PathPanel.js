/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * This panel is a breadcrumb showing the path from top level hierarchy structure all the way down to the one current in
 * view.
 */
//@todo The elements of the breadcrub should be clickable to jump to previous items.

Ext.define('lph.browser.content.generic.PathPanel', {
	/* Begin Definitions */
    extend	: 'Ext.toolbar.Toolbar',
   
    region	: 'north',
	height	: 23,
	frame	: false,
	border	: false,
	
	constructor: function(config){
		this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	"itemclick"	: true
        });
        
        elem = config.elem;
        node = config.node;

        this.createPath(elem);

        return this;
	},

	createPath: function(selectedNode){
         Ext.each(this._getParentPath(selectedNode), function(node){
            var btn = Ext.create('Ext.Button', {
                text    : node.get("text"),
                pressed : (node==selectedNode),
                node    : node,
                handler : this.buttonClicked,
                scope   : this
            });
            this.add(btn);
         },this);
    },

    buttonClicked: function(btn){
        this.fireEvent("itemclick", {"node" : btn.node});
    },

    _getParentPath: function(node){
        if(node.isRoot()){
            return [];
        }else{
            var aux = this._getParentPath(node.parentNode);
            aux.push(node);
            return aux;
        }

    }
});