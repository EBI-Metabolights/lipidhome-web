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

Ext.define('lph.browser.content.category.InfoPanel', {
	/* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',
    
    title	: 'Category info',
    layout	: 'border',
    border	: false,
        
    constructor : function(config){
    	this.callParent(arguments);
        this.initConfig(config);
        
        var lm = new Ext.LoadMask('loading', {msg: 'Loading...'});
        
        this.content = Ext.create('Ext.form.Panel',{
        	region		: 'west',
        	border		: false,
        	bodyPadding	: "20 50 0 20",
        	width		: 400,
        	layout		: 'anchor',
        	loadMask	: lm,
        	defaults	: {
        		anchor		: '100%',
        		labelWidth	: 130
    		},
    		//store		: config.store,
    		defaultType	: 'textfield',
		    items		: [{
		        fieldLabel: '# Main classes',
		        name: 'mainClasses',
		        readOnly: true
		    },{
		        fieldLabel: '# Species',
		        name: 'species',
		        readOnly: true
		    },{
		        fieldLabel: '# Sub species',
		        name: 'subSpecies',
		        readOnly: true
		    },{
		        fieldLabel: '# Annotated isomers',
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