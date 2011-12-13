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

        this.level= Ext.create('Ext.form.TextField', {
        	labelWidth	 : 150,
		    fieldLabel	 : 'Identification Resolution',
		    displayField : 'name',
            value        : 'Specie',
            readOnly     : true,
            allowBlank   : false,
            tooltip      : 'The Structural resolution of results'
		});
        this.level.addListener('render', this._onItemRender);
        this.add(this.level);

        Ext.form.VTypes['toleranceVal'] = /^(\d+(\.\d+)?)$/;
        Ext.form.VTypes['toleranceText'] = "Mass tolerance must be greater than or equal to zero.";
        Ext.form.VTypes['tolerance'] = function(v) {
            return Ext.form.VTypes['toleranceVal'].test(v);
        };

		this.tolerance = Ext.create('Ext.form.field.Number', {
            labelWidth	: 150,
			fieldLabel	: 'Mass Tolerance (Da)',
			value		: 0.2,
			step		: 0.1,
            minValue    : 0,
            allowBlank  : false,
            vtype       : 'tolerance',
            tooltip     : 'The mass tolerance applied to search masses'
		});
        this.tolerance.addListener('render', this._onItemRender);
        this.add(this.tolerance);
		
		this.identified = Ext.create('Ext.form.field.Checkbox',{
			boxLabel		: 'Identified',
			boxLabelAlign	: 'before',
            tooltip         : 'Return only identifed records'
		})
        this.identified.addListener('render', this._onItemRender);
        this.add(this.identified);

        return this;
    },

    validate: function(){
        return (this.level.validate() && this.tolerance.validate());
    },

    getData: function(){
        return {
            'level'      : this.level.getValue().toLowerCase(),
            'tolerance'  : this.tolerance.getValue(),
            'identified' : this.identified.getValue()
        };
    },

    _onItemRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;
        var title = !Ext.isEmpty(c.fieldLabel) ? c.fieldLabel : c.boxLabel;
        Ext.QuickTips.register({
            target  : c.getEl(),
            text    : c.tooltip,
            title   : title
        });
    }
});
