Ext.define('lph.browser.content.mainclass.InfoPanel', {
	/* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',
    
    title	: 'Main Class info',
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
		        fieldLabel: '# Sub classes',
		        name: 'subClasses',
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