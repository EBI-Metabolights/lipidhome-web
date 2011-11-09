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
    extend	: 'Ext.grid.Panel',

    title		: 'Sub Class List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
	}],
	
    columns: [
        {header: 'Code', dataIndex: 'code', filter: {type: 'string'}},
        {header: 'Name',  dataIndex: 'name', flex:1, filter: {type: 'string'}},
        {header: 'Radyl Chains', dataIndex: 'radylChains', filter: {type: 'numeric'}}
    ],
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
       
    	return this;
    }
});
