Ext.define('lph.tools.searchengine.input.SearchParamPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',

    frame       : true,
    border      : false,
    collapsible : true,
    collapsed   : false,
    padding     : '10 10 10 10',
    margin      : '5 0 0 0',
    title       : 'MS1 Search Options',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.resolution = Ext.create('Ext.data.Store', {
		    model: "ResolutionLevel",
		    data : [
		        {'id': 'specie', 'name':'Species'},
		        {'id': 'faScanSpecie', 'name':'FA Scan Species'},
		        {'id': 'subSpecie', 'name':'Sub Species'}
			]
		});
        
        this.combo = Ext.create('Ext.form.ComboBox', {
        	labelWidth	 : 150,
		    fieldLabel	 : 'Identification Resolution',
		    store		 : this.resolution,
		    queryMode	 : 'local',
		    displayField : 'name',
		    valueField	 : 'id',
            allowBlank   : false,
		});
        this.combo.select(this.combo.getStore().data.items[0]);
        this.add(this.combo);

        Ext.form.VTypes['toleranceVal'] = /^(\d+(\.\d+)?)$/;
        Ext.form.VTypes['toleranceText'] = "Mass tolerance must be greater than or equal to zero.";
        Ext.form.VTypes['tolerance'] = function(v) {
            return Ext.form.VTypes['toleranceVal'].test(v);
        };

		this.tolerance = Ext.create('Ext.form.field.Number', {
			fieldLabel	: 'Tolerance',
			value		: 0.2,
			step		: 0.1,
            minValue    : 0,
            allowBlank  : false,
            vtype       : 'tolerance'
		});
        this.add(this.tolerance);
		
		this.identified = Ext.create('Ext.form.field.Checkbox',{
			boxLabel		: 'Identified',
			boxLabelAlign	: 'before'
		})
        this.add(this.identified);

        return this;
    },

    validate: function(){
        return (this.combo.validate() && this.tolerance.validate());
    },

    getData: function(){
        return {
            'level'      : this.combo.getValue(),
            'tolerance'  : this.tolerance.getValue(),
            'identified' : this.identified.getValue()
        };
    }
});

Ext.define("ResolutionLevel",{
    extend: 'Ext.data.Model',
    fields: ['id', 'name'],
})