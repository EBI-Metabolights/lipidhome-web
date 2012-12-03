/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 * This Panel describes the Papers Grid that appears in the papers tab. It also describes the formatting of the
 * information contained when a row is expanded.
 */

//@todo  This must always be scrollable to fix some rendering bugs, this may be automatically fixed in future ext releases

Ext.define('lph.browser.content.generic.PapersPanel', {
	/* Begin Definitions */
    extend		: 'Ext.grid.Panel',

    title		 : 'Papers',
    iconCls      : 'papers-16',
    border		 : false,
    autoScroll	 : true,
	collapsible	 : true,
    animCollapse : false,
    disabled    : true,

	columns		 : [
		{header: 'PMID',  dataIndex: 'pmid'},
        {header: 'Title',  dataIndex: 'title', flex:1},
        {header: 'Date publish',  dataIndex: 'datePublish'},
        {header: 'Journal',  dataIndex: 'journalTitle'}
    ],
  
	loadRecord: function(data){
		var records = data.papers().getNewRecords();
		this.store.add(records);
		this._updateTab(records.length);
	},
	
	_updateTab: function(num){
		this.setTitle(this.title + " (" + num + ")");
		if(num>0) this.enable();
	},
	
	plugins: [{
        ptype: 'rowexpander',
        rowBodyTpl : [
            '<p><b>Authors:</b> {authors}</p><br>',
            '<p><b>Abstract:</b> {summary}</p>'
        ]
    }]    
});
