/**
 * Content pane is the container pane for the two main information panes. Theis pane containes many tabs, all of which
 * are invisible apart from the active tab. Inactive tabs ratehr than beign overwritten are simply set to invisible
 * this way if a user loads a tab, then want to view it again at a alter date, the data does not need to be fetched
 * twice just set to active and visible.
 */

Ext.define('lph.browser.content.ContentPane', {
	/* Begin Definitions */
    extend	: 'Ext.panel.Panel',
    
    region		: 'center',
	layout		: 'fit',

	listeners: {
		'tabchange': function(tabPanel, tab){
			//console.info(tabPanel, tab);
			//Ext.History.add(tabPanel.id + tokenDelimiter + tab.type + tokenDelimiter + tab.itemId);
			//Ext.History.add(tab.type + tokenDelimiter + tab.itemId);
		}
    },
    
    constructor: function(config){
		this.callParent(arguments);
        this.initConfig(config);
        
        this.manager = Ext.create('lph.browser.content.ContentManager');
        this.contained = null;

        var welcome = Ext.create('lph.browser.content.welcome.WelcomePane');
        this.addContent(welcome);

        return this;
    },
    
    loadContent: function(node, selections, obj){
    	var elem = selections[0];

    	if(elem!=undefined){
	    	var type = elem.get("type");
	    	var id = elem.get("itemId");
            var parentId = elem.parentNode.get("itemId");

	    	var panel = this.manager.getPanel(type, id, parentId, {node: node, elem: elem});

	    	if(!Ext.isEmpty(panel)){
                this.removeContent();
                this.addContent(panel);
		        //Ext.History.add(panel.type + tokenDelimiter + panel.itemId);
	    	}
    	}
    },

    removeContent: function(){
        if(this.contained!=null)
            this.remove(this.contained, false);
    },

    addContent: function(panel){
        this.add(panel);
        this.contained = panel
    }
});