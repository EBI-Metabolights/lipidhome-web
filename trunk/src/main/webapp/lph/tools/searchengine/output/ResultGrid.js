Ext.define('lph.tools.searchengine.output.ResultGrid', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    title       : 'Results',
    region      : 'center',

    features	: [{
		ftype : 'filters',
		local : true
	}],

    columns: [
        { header: 'Code', dataIndex: 'code', filter: {type: 'string' }},
        { header: 'Name', dataIndex: 'name', flex: 1, filter: {type: 'string' }},
        { header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean' }},
        { header: 'FA Carbons', dataIndex: 'faCarbons', filter: {type: 'int' }},
        { header: 'FA Double Bonds', dataIndex: 'faDoubleBonds', filter: {type: 'int' }},
        { header: 'Mass', dataIndex: 'resMass', filter: {type: 'float' }},
        { header: 'Delta', dataIndex: 'delta', filter: {type: 'float' }}
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
    }
});