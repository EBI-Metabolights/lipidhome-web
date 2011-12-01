Ext.define('lph.tools.searchengine.output.ResultGrid', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    region      : 'center',

    bbar: [{
        //xtype:'splitbutton',
        text: 'Export results...',
        //iconCls: 'add16',
        menu: [{
            text: 'CSV',
            //iconCls: 'add16',
            handler : function() {}
        },{
            text: 'Excel',
            //iconCls: 'add16',
            handler : function() {}
        },/*{
            text: 'MZ-Tab',
            //iconCls: 'add16',
            handler : function() {}
        },*/{
            text: 'XML',
            //iconCls: 'add16',
            handler : function() {}
        },{
            text: 'JSon',
            //iconCls: 'add16',
            handler : function() {}
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
        { header: 'Code', dataIndex: 'code', filter: {type: 'string' }, groupable: false},
        { header: 'Name', dataIndex: 'name', flex: 1, filter: {type: 'string' }, groupable: false},
        { header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean' }, groupable: false},
        { header: 'FA Carbons', dataIndex: 'faCarbons', filter: {type: 'int' }, groupable: false},
        { header: 'FA Double Bonds', dataIndex: 'faDoubleBonds', filter: {type: 'int' }, groupable: false},
        { header: 'Mass', dataIndex: 'resMass', filter: {type: 'float' }, groupable: false},
        { header: 'Delta', dataIndex: 'delta', filter: {type: 'float' }, groupable: false}
    ],

    /*
     * This fixes a bug found on the column redimension (delete it as soon as EXTjs fixes it)
     */
    _refresh: function(store, opts){
        var aux = this.getView().getHeaderCt();
        myAux = aux;
        for(i=0; i<aux.items.length; i++){
            aux.getHeaderAtIndex(i).hide();
            aux.getHeaderAtIndex(i).show();
        }
    },

});