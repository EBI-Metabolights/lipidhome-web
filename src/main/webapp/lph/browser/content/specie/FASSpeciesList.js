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
Ext.define('lph.browser.content.specie.FASSpeciesList', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    title		: 'Fatty Acid Scan Species List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
	}],
	
    columns: [
        {header: 'Name',  dataIndex: 'name', flex:1, filter: {type: 'string'}},
        {header: 'Identified',  dataIndex: 'identified', filter: {type: 'boolean'}},
        {header: 'Score',  dataIndex: 'score', filter: {type: 'numeric'}}
    ],
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
       
    	return this;
    }
});
