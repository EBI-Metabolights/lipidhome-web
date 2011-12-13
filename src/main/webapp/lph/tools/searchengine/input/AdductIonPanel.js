Ext.define('lph.tools.searchengine.input.AdductIonPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',

    frame       : true,
    border      : false,
    collapsible : true,
    collapsed   : false,
    padding     : '10 10 10 10',
    margin      : '5 0 0 0',
    title       : 'Adduct Ion Selection',
    layout      : 'anchor',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.checkboxgroup = Ext.create('Ext.form.CheckboxGroup',{
            anchor      : '100%',
            fieldLabel  : 'Select ions',
            tooltip     : 'Addcuts to be applied to database records',
            allowBlank  : false,
            msgTarget   : 'side',
            columns     : 2,
            vertical    : true,
            //items       : items
        });
        this.checkboxgroup.addListener('render', this._onItemRender, this);
        this.add(this.checkboxgroup);

        Ext.Ajax.request({
            url     : 'service/utils/adductions',
            success : this.createAdductIonItems,
            scope   : this
        });

        return this;
    },

    createAdductIonItems: function(response){
        var res = Ext.decode(response.responseText);

        var items = [];
        Ext.Array.each(res.list, function(adductIon){
            items.push({
                boxLabel: adductIon.name,
                name: adductIon.itemId,
                checked: adductIon.itemId==1,
                tooltip: "Mass = " + adductIon.mass,
                listeners : {render : {fn : this._onItemRender}}
            })
        }, this);

        this.checkboxgroup.add(items);
    },

    validate: function(){
        return this.checkboxgroup.validate();
    },

    getData: function(){
        var selected = [];
        var keys = Ext.Object.getKeys(this.checkboxgroup.getValue());
        Ext.Array.each(keys, function(key){
            selected.push(key);
        })
        return {'adductIons' : [Ext.encode(selected)]};
    },

    _onItemRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;

        var target = !Ext.isEmpty(c.labelEl) ? c.labelEl : c.getEl();
        var text = c.tooltip;
        var title = !Ext.isEmpty(c.fieldLabel) ? c.fieldLabel : c.boxLabel;

        Ext.QuickTips.register({
            target  : target,
            text    : text,
            title   : title
        });
    }
})

Ext.define("AdductIons",{
    extend: 'Ext.data.Model',
    fields: ['id', 'name', 'mass'],
})