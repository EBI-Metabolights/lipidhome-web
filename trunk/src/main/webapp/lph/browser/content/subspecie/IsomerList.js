/*
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * This Panel contains a grid that is a simple summary of all the direct children of the item in the InfoPanel,
 * it's columns are sortable, filterable and selectable.
 *
 */
Ext.define('lph.browser.content.subspecie.IsomerList', {
	/* Begin Definitions */
    extend	    : 'lph.browser.content.generic.GenericList',

    title		: 'Isomer List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
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
                    var identified = record.get("identified") ? "list-identified" : "list-unidentified" ;
                    return type + "-" + identified;
                }
            }]
        },
        {header: 'Name',  dataIndex: 'name', width:150, filter: {type: 'string'}, tooltip: 'LipidomicNet nomenclature name'},
        {header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean'}, tooltip: 'Identified in a paper/external resource'},
        //{header: 'Systematic name', dataIndex: 'systematicName', flex:1, filter: {type: 'string'}, tooltip: 'Systematic name'},
        {header: 'Smile', dataIndex: 'smile', flex:1, filter: {type: 'string'}, tooltip: 'Smile molecule representation'}
    ]
});
