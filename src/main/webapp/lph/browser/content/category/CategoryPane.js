/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The CategoryPane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */
Ext.define('lph.browser.content.category.CategoryPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);

        var text = config.elem.get('text')
        this.infoPanel = Ext.create('lph.browser.content.category.InfoPanel',{
            name : text,
            tabConfig : {
                tooltip  : 'General information about the category ' + text
            }
        });
        
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

/**
 * This is the Category model it is the information that will be displayed in the InfoPanel
 */
Ext.define('CategorySummaryModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'model', type: 'string' },
        { name: 'mainClasses', type: 'int' },
        { name: 'subClasses', type: 'int' },
        { name: 'species', type: 'int' },
        { name: 'faScanSpecies', type: 'int' },
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

/**
 * This model will be used to populate the mainClassList panel
 */
Ext.define('SimpleMainClass', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'type',  type : 'string', defaultValue: 'mainClass'},
        { name: 'name', type: 'string' },
        { name: 'code', type: 'string' }
    ]
});