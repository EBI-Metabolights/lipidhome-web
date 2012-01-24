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
Ext.define('lph.browser.content.specie.InfoPanel', {
	/* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',
    
    title	: 'Specie info',
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
    		defaultType	: 'textfield',
		    items		: [{
		        fieldLabel  : 'Name',
		        name        : 'name',
                tooltip     : 'LipidomicNet nomenclature name.',
		        readOnly    : true
		    },{
		        fieldLabel: 'Formula',
		        name: 'formula',
		        readOnly: true
		    },{
		        fieldLabel: 'Mass (Da)',
		        name: 'mass',
		        readOnly: true
		    },{
		        fieldLabel: 'Identified',
		        name: 'identified',
		        readOnly: true
		    },{
		        fieldLabel: '# FA Scan Species',
		        name: 'fasSpecies',
		        readOnly: true
		    },{
		        fieldLabel: '# Sub Species',
		        name: 'subSpecies',
		        readOnly: true
		    },{
		        fieldLabel: '# Annotated Isomers',
		        name: 'annotatedIsomers',
		        readOnly: true
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