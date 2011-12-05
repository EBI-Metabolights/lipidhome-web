Ext.define('lph.tools.searchengine.input.OptionsPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    width   : 400,
    frame   : true,
    layout  : {
        type    : 'vbox',
        pack    : 'start',
        align   : 'stretch'
    },

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.param = Ext.create('lph.tools.searchengine.input.SearchParamPanel');
        this.add(this.param);

        this.adductIons = Ext.create('lph.tools.searchengine.input.AdductIonPanel');
        this.add(this.adductIons);
        
        return this;
    },

    validate: function(){
        var paramValid = this.param.validate();
        var adductIonValid = this.adductIons.validate();
        return paramValid && adductIonValid;
    },

    getData: function(){
        return Ext.merge(this.param.getData(), this.adductIons.getData());
    }
});