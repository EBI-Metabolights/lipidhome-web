Ext.define('lph.browser.nav.NavPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    region		: 'west',
    split		: true,
    width		: 400,
    minWidth	: 300,
	maxWidth	: 600,
	frame		: false,
	border		: false,
	layout		: 'border',
	
	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.addEvents({
        	itemClicked	: true
        });
        
        this.search = Ext.create('lph.browser.nav.SearchPane');
        this.add(this.search);
               
        var store = Ext.create('Ext.data.TreeStore', {
        	fields : [
        		{name: 'itemId',  type : 'int'},
        		{name: 'name',  type : 'string'},
        		{name: 'type',  type : 'string', defaultValue: 'category'}
        	]
		});
			
        this.hierarchy = Ext.create('lph.browser.nav.HierarchyPane',{
        	store : store
        });
        this.add(this.hierarchy);
        
		this.loadTree();
  
        return this;
    },
    
    loadTree: function(){
    	Ext.Ajax.request({
        	url : 'service/category/list',
        	success: this.processResponse,
        	scope : this
        })
    },
    
    processResponse: function(response){
    	var node = this.hierarchy.getStore().getRootNode( )
    	var res = Ext.decode(response.responseText);
    	
    	Ext.each(res.list, function(item){
    		node.appendChild({
    			itemId	: item.itemId,
    			text	: item.name,
    			expanded: true,
    			type	: item.type
    		});
    	}, this);
    }
});