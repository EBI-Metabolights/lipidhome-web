Ext.define('lph.tools.searchengine.output.ResultGrid', {
	/* Begin Definitions */
    extend	: 'lph.tools.generic.ResultGrid',

    region      : 'center',

    columns: [
        {
            xtype       : 'actioncolumn',
            width       : 20,
            resizable   : false,
            hideable    : false,
            items       : [{
                getClass : function(v, metadata, record, rowIndex, colIndex, store) {
                    var type = record.get("type");
                    var identified = record.get("identified") ? "identified" : "unidentified" ;
                    return type + "-" + identified;
                }
            }]
        },
        { header: 'Code', dataIndex: 'code', groupable: false, tooltip:'Sub class code'},
        { header: 'Name', dataIndex: 'name', flex: 1, filter: {type: 'string' }, groupable: false, tooltip:'LipidomicNet nomenclature name'},
        { header: 'Mass', dataIndex: 'resMass', filter: {type: 'float' }, groupable: false, tooltip:'Exact adduct ion mass'},
        { header: 'Delta', dataIndex: 'delta', filter: {type: 'float' }, groupable: false, tooltip:'Search mass - Hit Mass'},
        { header: 'Adduct Ion', dataIndex: 'adductIon', filter: {type: 'string' }, groupable: false, tooltip:'Adduct ion code'},
        { header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean' }, groupable: false, tooltip:'Identified in a paper/external resource'},
        { header: 'FA Carbons', dataIndex: 'faCarbons', filter: {type: 'int' }, groupable: false, tooltip:'Total number of carbons in combined fatty acids'},
        { header: 'FA Double Bonds', dataIndex: 'faDoubleBonds', filter: {type: 'int' }, groupable: false, tooltip:'Total number of double bonds in combined fatty acids'}
    ],

    constructor: function(config) {
        var id = this.getId();
        Ext.apply(config, this._getBBarConfig(id));

    	this.callParent(arguments);
        this.initConfig(config);

        this.exportHelperID = "resultGridExportHelperID";
        this.exportHelperURL = "service/tools/export";
        this.exportHelperDataID = "resultGridExportDataID";
        this.exportHelperFormatID = "resultGridExportFormatID";
        this.exportHelper = Ext.create('Ext.Panel',{
            html : "<form id='"+ this.exportHelperID +"' method='post' action='"+this.exportHelperURL +"'>" +
                        "<input type='hidden' id='" + this.exportHelperDataID + "' name='data' />" +
                        "<input type='hidden' id='" + this.exportHelperFormatID + "' name='format' />" +
                    "</form>"
        })
        this.add(this.exportHelper);

        this.getStore().addListener('datachanged',this._onFilterUpdate, this);

        //Binding all the export buttons to
        Ext.getCmp(id + '-csv-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-tsv-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-excel-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-xml-button').addListener('click', this.exportData, this);
        Ext.getCmp(id + '-json-button').addListener('click', this.exportData, this);

        return this;
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

    _onFilterUpdate: function(store, opts){
        this.getStore().suspendEvents()
        this.filter();
        this.getStore().resumeEvents()
    },

    filter: function(node, checked, opts){
        var codes = [];
        Ext.Array.each(this.hierarchy.getChecked(),function(n){
            if(n.isLeaf()) codes.push(n.get("text"));
        })

        this.getStore().filterBy(function(r,id){
            var allowed = true;
            this.filters.filters.each(function(filter){
                if(filter.active)
                    allowed = (allowed && (filter.validateRecord(r)));
            });
            return allowed && Ext.Array.contains(codes,r.get("code"));
        },this);

        //TODO: Check if future versions of ext fix this problem (and then remove the next lines)
        this.hideVerticalScroller();
        this.determineScrollbars();
        this.initVerticalScroller();
        this.showVerticalScroller();
    },

    exportData: function(btn){
        data = [];
        this.getStore().data.each(function(row){
            data.push(row.data)
        });

        Ext.get(this.exportHelperDataID).dom.value = Ext.encode(data);
        Ext.get(this.exportHelperFormatID).dom.value = btn.format;
        Ext.get(this.exportHelperID).dom.submit();

    },

    /*
     * This fixes a bug found on the column redimension (delete it as soon as EXTjs fixes it)
     */
    _refresh: function(store, opts){
        var aux = this.getView().getHeaderCt();
        for(i=0; i<aux.items.length; i++){
            aux.getHeaderAtIndex(i).hide();
            aux.getHeaderAtIndex(i).show();
        }
    },

});
