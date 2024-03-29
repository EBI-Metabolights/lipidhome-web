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
Ext.define('lph.browser.content.mainclass.SubClassesList', {
	/* Begin Definitions */
    extend	    : 'lph.browser.content.generic.GenericList',

    title		: 'Sub Class List',
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
                    return type + "-list-identified";
                }
            }]
        },
        {header: 'Code', dataIndex: 'code', filter: {type: 'string'}, tooltip: 'LipidomicNet nomenclature main class code'},
        {header: 'Name',  dataIndex: 'name', flex:1, filter: {type: 'string'}, tooltip: 'LipidomicNet nomenclature name'},
        {header: 'Radyl Chains', dataIndex: 'radylChains', filter: {type: 'numeric'}, tooltip: 'Number of radyl chains attached to the sub class'},
        {
            xtype       : 'actioncolumn',
            width       : 20,
            resizable   : false,
            hideable    : false,
            sortable    : false,
            items       : [{
                getClass : function(v, metadata, record, rowIndex, colIndex, store) {
                    return "lph-grid-button";
                },
                tooltip : "Go to this lipid...",
                handler : function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    grid.fireEvent('itemdblclick', grid, rec, null, null, null);
                }
            }]
        }
    ]
});
