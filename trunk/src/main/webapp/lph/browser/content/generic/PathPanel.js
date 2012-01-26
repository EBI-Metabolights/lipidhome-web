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

Ext.define('lph.browser.content.generic.PathPanel', {
	/* Begin Definitions */
    extend	: 'Ext.toolbar.Toolbar',
   
    region	: 'north',
	height	: 38,
	frame	: false,
	border	: false,
    textLength : 20,
    enableOverflow: true,

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
            var text = node.get("text");
            var tooltip = false;
            if(text.length>this.textLength){
                var tooltip = true;
                text = text.substr(0,this.textLength-3  ) + "...";
            }
            var btnCfg = {
                text    : text,
                margin  : "0 0 0 2",
                pressed : (node==selectedNode),
                node    : node,
                handler : this.buttonClicked,
                //width   : 150,
                // The node's "identified" attribute is not available at this point,
                // so the next line is a patch in order to show the icon vertically
                iconCls : node.get("iconCls").replace("-","-list-"),
                scope   : this
            };
            if(tooltip) btnCfg.tooltip = node.get("text");
            var btn = Ext.create('Ext.Button', btnCfg);
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