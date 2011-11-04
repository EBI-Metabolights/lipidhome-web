Ext.define('lph.browser.content.subspecie.SubSpeciePane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.infoPanel = Ext.create('lph.browser.content.subspecie.InfoPanel');
        this.xrefPanel = Ext.create('lph.browser.content.generic.CrossReferencesPanel');
        this.papersPanel = Ext.create('lph.browser.content.generic.PapersPanel',{
        	store : Ext.create('Ext.data.ArrayStore', {model: 'Paper'})
        });

        config.model = 'SubSpecieSummaryModel';
		this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.addTab(this.xrefPanel);
        this.details.addTab(this.papersPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);
        
        this.list = Ext.create('lph.browser.content.subspecie.IsomerList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);

        return this;
    },
      
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleIsomer',
		    proxy: {
		        type: 'ajax',
		        url : 'service/subspecie/isomers',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {id : id}
		});
    }
});

Ext.define('SubSpecieSummaryModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'formula', type: 'string' },
        { name: 'mass', type: 'float' },
        { name: 'model', type: 'string' },
        { name: 'identified', type: 'boolean' },
        { name: 'subSpecies', type: 'int' },
        { name: 'annotatedIsomers', type: 'int' },
        { name: 'chain', type: 'string' }
    ],
    hasMany : [
    	{model: 'CrossReference', name: 'xrefs'},
    	{model: 'Paper', name: 'papers'}
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/subspecie/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});

Ext.define('SimpleIsomer', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'name', type: 'string' },
        { name: 'systematicName', type: 'string' },
        { name: 'identified', type: 'boolean' }
        
    ]
});