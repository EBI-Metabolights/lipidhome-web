Ext.define('lph.browser.content.ContentPane', {
	/* Begin Definitions */
    extend	: 'Ext.tab.Panel',
    
    region		: 'center',
	layout		: 'fit',
	id			: 'lsh',
	tabBar		: {
		hidden	: true
	},
	
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
                
        return this;
    },
    
    loadContent: function(node, selections, obj){
    	var elem = selections[0];
    	
    	if(elem!=undefined){
	    	var type = elem.data.type;
	    	var id = elem.data.itemId;
	    	
	    	var panel = this.manager.getPanel(type, id, {node: node, elem: elem});
	    	
	    	if(!Ext.isEmpty(panel)){
	    		this.add(panel);
		        this.setActiveTab(panel);
		        //Ext.History.add(panel.type + tokenDelimiter + panel.itemId);
	    	}
    	}
    }
});