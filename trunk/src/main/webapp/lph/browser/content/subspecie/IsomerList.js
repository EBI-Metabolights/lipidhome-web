/**
 * 
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 * 
 * @date August 2011
 */
Ext.define('lph.browser.content.subspecie.IsomerList', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

    title		: 'Isomer List',
    region		: 'center',
	frame		: false,
	border		: false,
	features	: [{
		ftype : 'filters',
		local : true
	}],
	
    columns: [
        {header: 'Name',  dataIndex: 'name', width:150, filter: {type: 'string'}},
        {header: 'Systematic name', dataIndex: 'systematicName', flex:1, filter: {type: 'string'}},
        {header: 'Identified', dataIndex: 'identified', filter: {type: 'boolean'}}
    ],
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
       
    	return this;
    }
});
