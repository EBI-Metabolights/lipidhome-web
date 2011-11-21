Ext.define('lph.tools.searchengine.input.InputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Input',
    layout	: 'border',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.opts = Ext.create('lph.tools.searchengine.input.OptionsPane');
        this.add(this.opts);
        
        this.massInput = Ext.create('lph.tools.searchengine.input.MassInputPanel');
        this.add(this.massInput);
        
        return this;
    }
});