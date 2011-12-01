/*
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 * This Panel is the BrowserTab it main function is to dived the Pane into the Navigation Pane and the ContentPane
 * This Pane also represents the last point at which the ContentPane and the NavigationPane are  linked and contains
 * the bindcontentActions function. This lets the contentPane listen to any addNode events in the navigator hierarchy
 * pane.
 *
*/

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
     * Every time a new tab is added to the content, a 'newContent' event is thrown and this method is
     * executed in order to listen to different events in the new tab
     * 
     * @param {} content contains the content tab elements to listen to
     */
    bindContentActions: function(content){
        //Binds a clicked item of a list to the hierarchy tree
        content.list.addListener('itemdblclick', Ext.bind(this.navigator.hierarchy.addNode, this.navigator.hierarchy, [content.itemId, content.type, content.identified], true));
        //Binds a clicked item of the path (button) to the hierarchy tree
        content.path.addListener('itemclick', this.navigator.hierarchy.selectNode, this.navigator.hierarchy);
    },

    manageLocationItemSelected: function(record){
        this.show();
        this.navigator.hierarchy.findPathTo(record);
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
