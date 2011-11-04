Ext.define('lph.tools.searchengine.input.SelectionHierarchyPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region	: 'center',
    html	: 'Tree',
    width	: 700,
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});