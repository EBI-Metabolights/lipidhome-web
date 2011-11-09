/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The FAScanSpeciePane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */

Ext.define('lph.browser.content.fascanspecie.FAScanSpeciePane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.infoPanel = Ext.create('lph.browser.content.fascanspecie.InfoPanel');
        this.xrefPanel = Ext.create('lph.browser.content.generic.CrossReferencesPanel');
        this.papersPanel = Ext.create('lph.browser.content.generic.PapersPanel',{
        	store : Ext.create('Ext.data.ArrayStore', {model: 'Paper'})
        });
        
        config.model = 'FAScanSpecieSummaryModel';
		this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.addTab(this.xrefPanel);
        this.details.addTab(this.papersPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);

        this.list = Ext.create('lph.browser.content.fascanspecie.SubSpeciesList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);

        return this;
    },
      
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleSubSpecie',
		    proxy: {
		        type: 'ajax',
		        url : 'service/fascanspecie/subspecies',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {id : id}
		});
    }
});

Ext.define('FAScanSpecieSummaryModel', {
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
            read: 'service/fascanspecie/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});

Ext.define('SimpleSubSpecie', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'name', type: 'string' },
        { name: 'identified', type: 'boolean' },
        { name: 'score', type: 'float' }
    ]
});