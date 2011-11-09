/**
 * This panel holds the tabs of the various tools associated with the database.
 */
Ext.define('lph.tools.ToolsPane', {
	/* Begin Definitions */
    extend		: 'Ext.tab.Panel',
    
    title		: 'Tools',
	tabPosition	: 'bottom',
	
	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.searchEngine = Ext.create('lph.tools.searchengine.SearchEnginePane');
        this.add(this.searchEngine);
        
        this.add({
        	title	: 'Other tool'
        	//,disabled: true
        })
        
        this.setActiveTab(this.searchEngine);
        
        return this;
	}
});