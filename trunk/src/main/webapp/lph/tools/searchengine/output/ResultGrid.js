Ext.define('lph.tools.searchengine.output.ResultGrid', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    features	: [{
		ftype : 'filters',
		local : true
	}],

    columns: [
        { header: 'Name', dataIndex: 'name', flex: 1, filter: {type: 'string' }},
        { header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean' }},
        { header: 'FA Carbons', dataIndex: 'faCarbons', filter: {type: 'int' }},
        { header: 'FA Double Bonds', dataIndex: 'faDoubleBonds', filter: {type: 'int' }},
        { header: 'Mass', dataIndex: 'resMass', filter: {type: 'float' }},
        { header: 'Delta', dataIndex: 'delta', filter: {type: 'float' }},
        { header: 'Code', dataIndex: 'code', filter: {type: 'string' }}
    ],

    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);

    	return this;
    }
});