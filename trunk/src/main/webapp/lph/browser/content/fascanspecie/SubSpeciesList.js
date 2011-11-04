/**
 * 
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 * 
 * @date August 2011
 */
Ext.define('lph.browser.content.fascanspecie.SubSpeciesList', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    title		: 'Sub Species List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
	}],
	
    columns: [
        {header: 'Name',  dataIndex: 'name', flex:1, filter: {type: 'string'}},
        {header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean'}},
        {header: 'Score', dataIndex: 'score', filter: {type: 'numeric'}}
    ],
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
       
    	return this;
    }
});
