Ext.define('lph.tools.searchengine.input.MassInputPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',
   
    region	: 'center',
    layout  : 'fit',
    padding : '10 10 10 10',
    frame   : true,

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        Ext.form.VTypes['massesVal'] = /^(\d+\.\d+\n?)+$/;
        Ext.form.VTypes['massesText'] = "Input is not a list of new line separated decimal masses";
        Ext.form.VTypes['masses'] = function(v) {
            return Ext.form.VTypes['massesVal'].test(v);
        };


        this.textArea = Ext.create('Ext.form.field.TextArea',{
            name        : 'masses',
            allowBlank  : false,
            labelAlign  : 'top',
            fieldLabel  : 'List of Precursor Ion Masses',
            emptyText	: 'Paste in here your list of Precursor Ion Masses...',
            vtype       : 'masses'
        });
        this.add(this.textArea);


        return this;
    },

    validate: function(){
        return this.textArea.validate();
    },

    getData: function(){
        return {
            'masses'  : this.textArea.getValue()
        }
    }

});