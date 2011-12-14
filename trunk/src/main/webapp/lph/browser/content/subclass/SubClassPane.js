/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The SubClassPane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */

Ext.define('lph.browser.content.subclass.SubClassPane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);

        this.infoPanel = Ext.create('lph.browser.content.subclass.InfoPanel',{
            tabConfig : {
                tooltip  : 'General information about the sub class ' + config.elem.get('text')
            }
        });
        
        config.model = 'SubClassSummaryModel';
		this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);
        
        this.list = Ext.create('lph.browser.content.subclass.SpeciesList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);
        
        return this;
    },
      
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleSpecie',
		    proxy: {
		        type: 'ajax',
		        url : 'service/subclass/species',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {'id': id}
		});
    }
});

Ext.define('SubClassSummaryModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'model', type: 'string' },
        { name: 'species', type: 'int' },
        { name: 'subSpecies', type: 'int' },
        { name: 'annotatedIsomers', type: 'int' }
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/subclass/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});

Ext.define('SimpleSpecie', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'type',  type : 'string', defaultValue: 'specie'},
        { name: 'name', type: 'string' },
        { name: 'identified', type: 'boolean' },
        { name: 'carbons', type: 'int' },
        { name: 'doubleBonds', type: 'int' },
        { name: 'score', type: 'float' },
        { name: 'formula', type: 'string' },
        { name: 'mass', type: 'float' }
    ]
});