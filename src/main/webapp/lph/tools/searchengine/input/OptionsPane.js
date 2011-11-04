Ext.define('lph.tools.searchengine.input.OptionsPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region	: 'west',
    width	: 700,
    layout	: 'border',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.hierarchy = Ext.create('lph.tools.searchengine.input.SelectionHierarchyPanel');
        this.add(this.hierarchy);
        
        this.param = Ext.create('lph.tools.searchengine.input.SearchParamPanel');
        this.add(this.param);
        
        return this;
    }
});