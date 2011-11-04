Ext.define('lph.browser.content.fascanspecie.InfoPanel', {
	/* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',
    
    title	: 'FA scan info',
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
		        fieldLabel: 'Formula',
		        name: 'formula',
		        readOnly: true
		    },{
		        fieldLabel: 'Mass',
		        name: 'mass',
		        readOnly: true
		    },{
		        fieldLabel: 'Identified',
		        name: 'identified',
		        readOnly: true
		    },{
		        fieldLabel: '# Sub Species',
		        name: 'subSpecies',
		        readOnly: true
		    },{
		        fieldLabel: '# Annotated Isomers',
		        name: 'annotatedIsomers',
		        readOnly: true
		    },{
		        fieldLabel: 'R1 + R2',
		        name: 'chain',
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