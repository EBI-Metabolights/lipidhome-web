Ext.define('lph.browser.content.category.CategoryPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.infoPanel = Ext.create('lph.browser.content.category.InfoPanel');
        
        config.model = 'CategorySummaryModel';
        this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);

        this.list = Ext.create('lph.browser.content.category.MainClassesList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);
       
        return this;
    },
         
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleMainClass',
		    proxy: {
		        type: 'ajax',
		        url : 'service/category/mainclasses',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {'id': id}
		});
    }
});

Ext.define('CategorySummaryModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'model', type: 'string' },
        { name: 'mainClasses', type: 'int' },
        { name: 'species', type: 'int' },
        { name: 'subSpecies', type: 'int' },
        { name: 'annotatedIsomers', type: 'int' }
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/category/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});

Ext.define('SimpleMainClass', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'name', type: 'string' },
        { name: 'code', type: 'string' }
    ]
});