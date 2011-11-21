Ext.define('lph.tools.searchengine.input.MassInputPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',
   
    region	: 'center',
    padding : '10 10 10 10',
    frame   : true,

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.textArea = Ext.create('Ext.form.field.TextArea',{
            name        : 'masses',
            labelAlign  : 'top',
            fieldLabel  : 'List of Precursor Ion Masses',
            emptyText	: 'Paste in here your list of Precursor Ion Masses...',
            anchor      : '100%',
            height      : '100%'
        });
        this.add(this.textArea);


        return this;
    }
});