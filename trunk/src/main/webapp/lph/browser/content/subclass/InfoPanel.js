/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 * This Panel contains the summary information about a category, it also displays  an image of the molecule using
 * a modified ChemdoodleWeb library that supports the rendering of R chains (by adding them to the ELEMENT array.)
 */
Ext.define('lph.browser.content.subclass.InfoPanel', {
	/* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',
    
    title	: 'Sub Class info',
    layout	: 'border',
    border	: false,
        
    constructor : function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.content = Ext.create('Ext.form.Panel',{
        	region		: 'west',
        	border		: false,
        	bodyPadding	: "20 50 0 20",
        	width		: 400,
        	layout		: 'anchor',
        	defaults	: {
        		anchor		: '100%',
        		labelWidth	: 130
    		},
    		//store		: config.store,
    		defaultType	: 'textfield',
		    items		: [{
		        fieldLabel  : 'Name',
		        name        : 'name',
                tooltip     : 'LipidomicNet nomenclature name.',
		        readOnly    : true
		    },{
		        fieldLabel  : '# Species',
		        name        : 'species',
                tooltip     : 'The number of species that belong to this sub class.',
		        readOnly    : true
		    },{
		        fieldLabel  : '# FA scan species',
		        name        : 'faScanSpecies',
                tooltip     : 'The number of fatty acid scan species that belong to this sub class.',
		        readOnly    : true
		    },{
		        fieldLabel  : '# Sub species',
		        name        : 'subSpecies',
                tooltip     : 'The number of sub species that belong to this sub class.',
		        readOnly    : true
		    },{
		        fieldLabel  : '# Annotated isomers',
		        name        : 'annotatedIsomers',
                tooltip     : 'The number of isomers for which there is evidence in external resources that belong to this sub class.',
		        readOnly    : true
		    }]
        });
        this.add(this.content);
        
        this.moleculeViewer = Ext.create('lph.browser.content.generic.MoleculeViewer',{
			region	: 'center'
		})
        this.add(this.moleculeViewer);
        
        return this;
    }
});