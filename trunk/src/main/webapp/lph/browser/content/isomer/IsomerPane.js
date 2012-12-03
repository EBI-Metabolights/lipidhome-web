/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date October 2012
 *
 *
 */

Ext.define('lph.browser.content.isomer.IsomerPane', {
    /* Begin Definitions */
    extend		: 'Ext.panel.Panel',

    layout		: 'border',
    border		: false,

    constructor: function(config){
        this.callParent(arguments);
        this.initConfig(config);

        var text = config.elem.get('text');

        this.smile = null;
        this.identified = false;
        try{
            this.smile = config.elem.get("extra").get("smile");
            this.identified = config.elem.get("extra").get("identified");
        }catch(e){}

        this.infoPanel = Ext.create('lph.browser.content.isomer.InfoPanel', {
            name : text,
            smile : this.smile,
            region: 'center',
            tabConfig : {
                tooltip  : 'General information about the isomer ' + text
            }
        });
        this.xrefPanel = Ext.create('lph.browser.content.generic.CrossReferencesPanel', {
            tabConfig : {
                tooltip  : 'External datasources with information about the isomer ' + text
            }
        });

        config.model = 'Isomer';
        config.region = 'center'; //Only happens in ISOMER PANE because there is not list to add in the center region
        config.identified = this.identified;
        this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.addTab(this.xrefPanel);

        this.add(this.details);

        return this;
    }
});

Ext.define('Isomer', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'type',  type : 'string', defaultValue: 'isomer'},
        { name: 'name', type: 'string' },
        //{ name: 'systematicName', type: 'string' },
        { name: 'smile', type: 'string' },
        { name: 'identified', type: 'boolean' }
    ],
    hasMany : [
        {model: 'CrossReference', name: 'xrefs'},
        {model: 'Paper', name: 'papers'}
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/isomer/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});