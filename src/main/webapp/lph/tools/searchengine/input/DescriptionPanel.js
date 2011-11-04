Ext.define('lph.tools.searchengine.input.DescriptionPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region		: 'east',
    width		: 300,
	title		: 'Description',
	collapsible	: true,
	//animCollapse: true,
	layout		: 'fit',
	html		: '<p>Description of the tool</p>',
	
	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});