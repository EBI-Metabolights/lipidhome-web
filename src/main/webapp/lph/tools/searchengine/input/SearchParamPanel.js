Ext.define('lph.tools.searchengine.input.SearchParamPanela', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',

    layout  : {
        type    : 'vbox',
        align   : 'right'
    },
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.label = Ext.create('Ext.form.Label',{
            text    : 'Does it look nice?'
        });
        this.add(this.label);

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
		    fieldLabel	 : 'Identification Resolutions',
		    store		 : this.resolution,
		    queryMode	 : 'local',
		    displayField : 'name',
		    valueField	 : 'id'
		});
		this.add(this.combo);
		
		this.tolerance = Ext.create('Ext.form.field.Number', {
            labelAlign  : 'top',
			fieldLabel	: 'Tolerance',
			value		: 0.2,
			step		: 0.1
		});
		this.add(this.tolerance);
		
		this.identified = Ext.create('Ext.form.field.Checkbox',{
			boxLabel		: 'Identified',
			boxLabelAlign	: 'before'
		})
		this.add(this.identified);
		
        return this;
    }
});