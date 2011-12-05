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
                boxLabel: adductIon.name, name: adductIon.itemId,  checked: adductIon.itemId==1
            })
        }, this);

        this.checkboxgroup = Ext.create('Ext.form.CheckboxGroup',{
            anchor      : '100%',
            fieldLabel  : 'Select ions',
            allowBlank  : false,
            msgTarget   : 'side',
            columns     : 2,
            vertical    : true,
            items       : items
        });
        this.add(this.checkboxgroup);
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
    }
})

Ext.define("AdductIons",{
    extend: 'Ext.data.Model',
    fields: ['id', 'name', 'mass'],
})