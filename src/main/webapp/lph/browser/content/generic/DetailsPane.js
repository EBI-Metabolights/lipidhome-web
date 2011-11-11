Ext.define('lph.browser.content.generic.DetailsPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    region	: 'north',
    split	: true,
    height	: 300,
    layout	: 'border',
    frame   : false,
    border	: false,
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
     
        this.path = Ext.create('lph.browser.content.generic.PathPanel', {
			node		 : config.node,
			elem		 : config.elem
        });
        this.add(this.path);
   
        this.summaryPane = Ext.create('lph.browser.content.generic.SummaryPane');
        this.add(this.summaryPane);
        
        this.addListener('afterrender', this.onRendered, this);
        
        return this;
    },
    
    addTab: function(tab){
    	this.summaryPane.addTab(tab);
    },
    
    setActiveTab: function(tab){
    	this.summaryPane.setActiveTab(tab);
    },
    
    onRendered: function(){
    	this.summaryPane.showMask();
    	Ext.ModelMgr.getModel(this.model).load(this.itemId, {
        	scope	: this.summaryPane,
	    	success	: this.summaryPane.loadData,
	    	failure	: this.summaryPane.showErrorMessage,
	    	callback: this.summaryPane.hideMask
		});
    }
});