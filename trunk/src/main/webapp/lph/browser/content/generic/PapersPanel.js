Ext.define('lph.browser.content.generic.PapersPanel', {
	/* Begin Definitions */
    extend		: 'Ext.grid.Panel',
    
    
    title		 : 'Papers',
    border		 : false,
    autoScroll	 : true,
	collapsible	 : true,
    animCollapse : false,
    
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
		if(num==0) this.disable();
	},
	
	plugins: [{
        ptype: 'rowexpander',
        rowBodyTpl : [
            '<p><b>Authors:</b> {authors}</p><br>',
            '<p><b>Abstract:</b> {summary}</p>'
        ]
    }]    
});
