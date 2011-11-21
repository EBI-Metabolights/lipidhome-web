/**
 * This is the Search engine tab and contains the two major Panels for its use, input and output.
 */
Ext.define('lph.tools.searchengine.SearchEnginePane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Search engine',
    layout  : 'border',
    
    constructor : function(config){
    	this.callParent(arguments);
    	this.initConfig(config);

        var accordion = Ext.create('Ext.panel.Panel',{
            layout	: 'accordion',
            region  : 'center'
        })

        this.input = Ext.create('lph.tools.searchengine.input.InputPane');
        accordion.add(this.input);
        
        
        this.output = Ext.create('lph.tools.searchengine.output.OutputPane');
	    accordion.add(this.output);

        this.add(accordion);

        this.description = Ext.create('lph.tools.searchengine.input.DescriptionPanel',{
			collapsed	: true
        });
        this.add(this.description);
	    
        return this;
    }
    
});