Ext.define('lph.tools.searchengine.input.SearchParamPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',
    region	: 'center',
    frame   : true,
    border  : false,
    padding : '10 0 10 10',
    layout  : {
        type    : 'vbox',
        align   : 'right'
    },
    buttons: [{
        text    : 'Reset',
        handler : this.resetFn,
        scope   : this
    },{
        text    : 'Submit',
        formBind: true, //only enabled once the form is valid
        disabled: true,
        handler : this.submitFn,
    }],

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.fieldset = Ext.create('Ext.form.FieldSet',{
            title    : 'MS1 Search Options',
            layout: 'anchor',
            defaults: {
                anchor: '100%'
            }
        });

        this.resolution = Ext.create('Ext.data.Store', {
		    fields: ['id', 'name'],
		    data : [
		        {'id': 1, 'name':'Species'},
		        {'id': 2, 'name':'FA Scan Species'},
		        {'id': 3, 'name':'Sub Species'}
			]
		});
        
        this.combo = Ext.create('Ext.form.ComboBox', {
        	labelWidth	 : 150,
		    fieldLabel	 : 'Identification Resolution',
		    store		 : this.resolution,
		    queryMode	 : 'local',
		    displayField : 'name',
		    valueField	 : 'id'
		});
		this.fieldset.add(this.combo);
		
		this.tolerance = Ext.create('Ext.form.field.Number', {
			fieldLabel	: 'Tolerance',
			value		: 0.2,
			step		: 0.1
		});
		this.fieldset.add(this.tolerance);
		
		this.identified = Ext.create('Ext.form.field.Checkbox',{
			boxLabel		: 'Identified',
			boxLabelAlign	: 'before'
		})
		this.fieldset.add(this.identified);

        this.add(this.fieldset);

        return this;
    },

    resetFn: function(){
        console.info("reset");
    },

    submitFn: function(){
        console.info("submit");
    }
});