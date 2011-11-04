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