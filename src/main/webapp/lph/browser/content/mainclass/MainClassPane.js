/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The MainClassPane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */

Ext.define('lph.browser.content.mainclass.MainClassPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.infoPanel = Ext.create('lph.browser.content.mainclass.InfoPanel');
        
        config.model = 'MainSummaryModel';
        this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);
        
        this.list = Ext.create('lph.browser.content.mainclass.SubClassesList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);
        
        return this;
    },
    
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleSubClass',
		    proxy: {
		        type: 'ajax',
		        url : 'service/mainclass/subclasses',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {'id': id}
		});
    }
});

Ext.define('MainSummaryModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'model', type: 'string' },
        { name: 'subClasses', type: 'int' },
        { name: 'species', type: 'int' },
        { name: 'subSpecies', type: 'int' },
        { name: 'annotatedIsomers', type: 'int' }
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/mainclass/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});

Ext.define('SimpleSubClass', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'name', type: 'string' },
        { name: 'code', type: 'string' },
        { name: 'radylChains', type: 'int' }
    ]
});