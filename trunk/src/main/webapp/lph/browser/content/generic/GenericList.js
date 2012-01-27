Ext.define('lph.browser.content.generic.GenericList', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    constructor : function(config){
        var id = this.getId();
        Ext.apply(config, this._getBBarConfig(id));

    	this.callParent(arguments);
        this.initConfig(config);

        this.exportHelper = this._generateExportPanel(id);
        this.add(this.exportHelper);

        this.addListener('render', this._onRender);

        //Binding all the export buttons to
        Ext.getCmp(id + '-csv-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-tsv-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-excel-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-xml-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-json-button').addListener('click', this.exportData, this);

        return this;
    },

    _onRender: function(){
        this._enableTooltips();
    },

    _enableTooltips: function(){
        if(Ext.isEmpty(this.columns)) return;

        Ext.Array.each(this.columns, function(c){
            c.addListener('render', this._onColumnRender)
        }, this);
    },

    _onColumnRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;

        Ext.QuickTips.register({
            target  : c.getEl(),
            text    : c.tooltip,
            title   : c.text
            //width   : 300
        });
    },

    _getBBarConfig: function(id){
        config = {
            bbar : [{
                //xtype :'splitbutton',
                text    : 'Export results...',
                iconCls : 'export-16',
                menu: [{
                    id      : id + '-csv-button',
                    text    : 'CSV',
                    format  : 'csv',
                    iconCls : 'csv-export-16',
                },{
                    id      : id + '-tsv-button',
                    text    : 'TSV',
                    format  : 'tsv',
                    iconCls : 'tsv-export-16',
                },{
                    id      : id + '-excel-button',
                    text    : 'MS Excel',
                    format  : 'excel',
                    iconCls : 'excel-export-16',
                },/*{
                    id      : id + '-mztab-button',
                    text    : 'MZ-Tab',
                    format  : 'mztab',
                    //iconCls: 'add16',
                },*/{
                    id      : id + '-xml-button',
                    text    : 'XML',
                    format  : 'xml',
                    iconCls : 'xml-export-16',
                },{
                    id      : id + '-json-button',
                    text    : 'JSon',
                    format  : 'json',
                    iconCls : 'json-export-16',
                }]
            }]
        };
        return config;
    },

    _generateExportPanel: function(id){
        this.exportHelperID = id + "ExportHelperID";
        this.exportHelperURL = "service/utils/export";
        this.exportHelperDataID = id + "ExportDataID";
        this.exportHelperFormatID = id + "ExportFormatID";
        return Ext.create('Ext.Panel',{
            html : "<form id='"+ this.exportHelperID +"' method='post' action='"+this.exportHelperURL +"'>" +
                        "<input type='hidden' id='" + this.exportHelperDataID + "' name='data' />" +
                        "<input type='hidden' id='" + this.exportHelperFormatID + "' name='format' />" +
                    "</form>"
        })
    },

    exportData: function(btn){
        data = [];
        this.getStore().data.each(function(row){
            data.push(row.data)
        });

        Ext.get(this.exportHelperDataID).dom.value = Ext.encode(data);
        Ext.get(this.exportHelperFormatID).dom.value = btn.format;
        Ext.get(this.exportHelperID).dom.submit();
    }
});