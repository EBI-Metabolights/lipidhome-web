/**
 * 
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 * 
 * @date August 2011
 */
Ext.define('lph.browser.content.category.MainClassesList', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    title		: 'Main Class List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
	}],
	
    columns: [
        {header: 'Code', dataIndex: 'code', filter: {type: 'string'}},
        {header: 'Name',  dataIndex: 'name', flex: 1, filter: {type: 'string'}}
    ],
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
    	return this;
    }
});
