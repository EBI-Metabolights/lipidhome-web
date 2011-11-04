/**
 * 
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
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
