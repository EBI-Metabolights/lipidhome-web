/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The SpeciePane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */

Ext.define('lph.browser.content.specie.SpeciePane', {
	/* Begin Definitions */
    extend		: 'Ext.Panel',
    
    layout		: 'border',
    border		: false,
	
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);

        var text = config.elem.get('text');

        this.infoPanel = Ext.create('lph.browser.content.specie.InfoPanel',{
            tabConfig : {
                tooltip  : 'General information about tne specie ' + text
            }
        });
        this.xrefPanel = Ext.create('lph.browser.content.generic.CrossReferencesPanel', {
            tabConfig : {
                tooltip  : 'External datasources with information about the specie ' + text
            }
        });
        this.papersPanel = Ext.create('lph.browser.content.generic.PapersPanel',{
            tabConfig : {
                tooltip  : 'Papers in which the specie ' + text + ' is mentioned in the abstract'
            },
        	store : Ext.create('Ext.data.ArrayStore', {model: 'Paper'})
        });
        
        config.model = 'SpecieModel';
		this.details = Ext.create('lph.browser.content.generic.DetailsPane', config);
        this.details.addTab(this.infoPanel);
        this.details.addTab(this.xrefPanel);
        this.details.addTab(this.papersPanel);
        this.details.setActiveTab(this.infoPanel);
        this.add(this.details);

        this.list = Ext.create('lph.browser.content.specie.FASSpeciesList',{
        	store : this._getListStore(this.itemId)
        });
        this.add(this.list);
        
        return this;
    },
          
    _getListStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'SimpleFASSpecie',
		    proxy: {
		        type: 'ajax',
		        url : 'service/specie/fascanspecies',
		        reader: {
		            type: 'json',
		            root: 'list'
		        }
		    },
		    autoLoad: {id : id}
		});
    }
});

Ext.define('SpecieModel', {
    extend: 'Ext.data.Model',
    url : '',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'formula', type: 'string' },
        { name: 'mass', type: 'float' },
        { name: 'model', type: 'string' },
        { name: 'identified', type: 'boolean' },
        { name: 'fasSpecies', type: 'int' },
        { name: 'subSpecies', type: 'int' },
        { name: 'annotatedIsomers', type: 'int' }
    ],
    hasMany : [
    	{model: 'CrossReference', name: 'xrefs'},
    	{model: 'Paper', name: 'papers'}
    ],
    proxy: {
        type: 'ajax',
        api: {
            read: 'service/specie/summary'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});



Ext.define('SimpleFASSpecie', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'type',  type : 'string', defaultValue: 'faScanSpecie'},
        { name: 'name', type: 'string' },
        { name: 'identified', type: 'boolean' },
        { name: 'score', type: 'float' }
    ]
});
