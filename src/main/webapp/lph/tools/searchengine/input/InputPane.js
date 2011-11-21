Ext.define('lph.tools.searchengine.input.InputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    title	: 'Input',
    layout	: 'border',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.massInput = Ext.create('lph.tools.searchengine.input.MassInputPanel',{
            region	: 'center'
        });
        this.add(this.massInput);

        this.opts = Ext.create('lph.tools.searchengine.input.OptionsPane',{
            region	: 'east'
        });
        this.add(this.opts);

        return this;
    }
});