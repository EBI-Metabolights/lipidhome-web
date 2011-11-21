Ext.define('lph.tools.searchengine.input.SelectionHierarchyPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region	: 'west',
    html	: 'Tree',
    width	: 300,
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});