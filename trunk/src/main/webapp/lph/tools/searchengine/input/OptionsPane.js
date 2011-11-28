Ext.define('lph.tools.searchengine.input.OptionsPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    width   : 350,
    layout	: 'border',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.param = Ext.create('lph.tools.searchengine.input.SearchParamPanel');
        this.add(this.param);
        
        return this;
    },

    validate: function(){
        return this.param.validate();
    },

    getData: function(){
        return this.param.getData();
    }
});