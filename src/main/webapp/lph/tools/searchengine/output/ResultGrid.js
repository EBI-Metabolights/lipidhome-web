Ext.define('lph.tools.searchengine.output.ResultGrid', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    region      : 'center',

    bbar: [{
        //xtype :'splitbutton',
        text    : 'Export results...',
        iconCls : 'export-16',
        menu: [{
            id      : 'resultgrid-csv-button',
            text    : 'CSV',
            format  : 'csv',
            //iconCls : 'add16',
        },{
            id      : 'resultgrid-excel-button',
            text    : 'MS Excel',
            format  : 'excel',
            //iconCls : 'add16',
        },/*{
            id      : 'grid-mztab-button',
            text    : 'MZ-Tab',
            format  : 'mztab',
            //iconCls: 'add16',
        },*/{
            id      : 'resultgrid-xml-button',
            text    : 'XML',
            format  : 'xml',
            //iconCls: 'add16',
        },{
            id      : 'resultgrid-json-button',
            text    : 'JSon',
            format  : 'json',
            //iconCls: 'add16',
        }]
    }],

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
        { header: 'Code', dataIndex: 'code', groupable: false},
        { header: 'Name', dataIndex: 'name', flex: 1, filter: {type: 'string' }, groupable: false},
        { header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean' }, groupable: false},
        { header: 'FA Carbons', dataIndex: 'faCarbons', filter: {type: 'int' }, groupable: false},
        { header: 'FA Double Bonds', dataIndex: 'faDoubleBonds', filter: {type: 'int' }, groupable: false},
        { header: 'Mass', dataIndex: 'resMass', filter: {type: 'float' }, groupable: false},
        { header: 'Delta', dataIndex: 'delta', filter: {type: 'float' }, groupable: false}
    ],

    constructor: function(config) {
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
        Ext.getCmp('resultgrid-csv-button').addListener('click', this.exportData, this);
        Ext.getCmp('resultgrid-excel-button').addListener('click', this.exportData, this);
        Ext.getCmp('resultgrid-xml-button').addListener('click', this.exportData, this);
        Ext.getCmp('resultgrid-json-button').addListener('click', this.exportData, this);

        return this;
    },

    _onFilterUpdate: function(store, opts){
        store.suspendEvents();
        this.filter();
        store.resumeEvents();
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
        console.info(btn.format);
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
