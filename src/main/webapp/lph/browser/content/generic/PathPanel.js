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
    extend	: 'Ext.Panel',
   
    region	: 'north',
	height	: 25,
	frame	: true,
	border	: false,
	
	constructor: function(config){
		this.callParent(arguments);
        this.initConfig(config);
        
        elem = config.elem;
        node = config.node;

        this.update(this.getPath(elem));
        
        return this;
	},
	
	getPath: function(node){
		if(node.isRoot()){
			return "";
		}else{
			var path = this.getPath(node.parentNode);
			var sep =  Ext.isEmpty(path)?"":" >> ";
			return path + sep + node.get("text");
		}
	}
	
	
});