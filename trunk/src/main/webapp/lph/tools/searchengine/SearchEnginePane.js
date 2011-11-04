Ext.define('lph.tools.searchengine.SearchEnginePane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Search engine',
    layout	: 'accordion',
    
    constructor : function(config){
    	this.callParent(arguments);
    	this.initConfig(config);
    	       
        this.input = Ext.create('lph.tools.searchengine.input.InputPane');
        this.add(this.input);
        
        
        this.output = Ext.create('lph.tools.searchengine.output.OutputPane');
	    this.add(this.output);
	    
        return this;
    }
    
});