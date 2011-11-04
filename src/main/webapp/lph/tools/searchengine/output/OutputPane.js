Ext.define('lph.tools.searchengine.output.OutputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Output',
    //layout	: 'border',
    html	: 'Outputs',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        return this;
    }
});