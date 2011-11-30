Ext.define('lph.tools.searchengine.output.SelectionHierarchyPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    html	: 'Tree',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});