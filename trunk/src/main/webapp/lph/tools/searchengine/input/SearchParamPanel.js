Ext.define('lph.tools.searchengine.input.SearchParamPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
   
    region	: 'east',
    layout: {
        type: 'vbox',
        align: 'rigth'
    },
    width	: 350,
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        
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
		this.add(this.combo);
		
		this.tolerance = Ext.create('Ext.form.field.Number', {
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