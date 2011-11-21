Ext.define('lph.tools.searchengine.output.OutputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Output',
    layout	: 'border',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.hierarchy = Ext.create('lph.tools.searchengine.input.SelectionHierarchyPanel');
        this.add(this.hierarchy);

        this.result = Ext.create('Ext.Panel',{
            region  : 'center',
            html	: 'Outputs',
        });
        this.add(this.result);

        return this;
    }
});