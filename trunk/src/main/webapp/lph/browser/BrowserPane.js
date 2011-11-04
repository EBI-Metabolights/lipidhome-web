Ext.define('lph.browser.BrowserPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Browser',
    layout	: 'border',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.navigator = Ext.create('lph.browser.nav.NavPane');
        this.add(this.navigator);
        
        this.content = Ext.create('lph.browser.content.ContentPane');
        this.add(this.content);
		
        this.navigator.hierarchy.addListener('selectionChange', this.content.loadContent, this.content);
        this.content.manager.addListener('newContent', this.bindContentActions, this);
        
        return this;
    },
    
    /**
     * Everytime a new tab is added to the content, a 'newContent' event is thrown and this method is
     * executed in order to listen to different events in the new tab
     * 
     * @param {} content contains the content tab elements to listen to
     */
    bindContentActions: function(content){
    	content.list.addListener('itemclick', Ext.bind(this.navigator.hierarchy.addNode, this.navigator.hierarchy, [content.type, content.itemId], true));
    }
});

/** COMMON MODELS **/
//Is here while testing, PLEASE MOVE IT TO SOME OTHER BETTER PLACE!!
//TODO: Joe, move this please xDD
Ext.define('CrossReference', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'resource', type: 'string'},
        {name: 'url',    type: 'string'}
    ]
});

Ext.define('Paper', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'pmid', type: 'int' },
        { name: 'title', type: 'string' },
        { name: 'datePublish', type: 'string' },
        { name: 'journalTitle', type: 'string' },
        { name: 'journalData', type: 'string' },
        { name: 'authors', type: 'string' },
        { name: 'meshTerms', type: 'string' },
        { name: 'summary', type: 'string' }
    ]
});
