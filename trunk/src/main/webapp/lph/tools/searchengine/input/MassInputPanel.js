Ext.define('lph.tools.searchengine.input.MassInputPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region	: 'center',
    html	: 'Search engine!',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});