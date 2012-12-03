Ext.define('lph.browser.content.generic.SummaryPane', {
	/* Begin Definitions */
    extend	: 'Ext.form.Panel',
    
    region	: 'center',
    layout	: 'fit',
    border	: false,
    
    tabs	: null,
    
    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);
                
        this.panel = Ext.create('Ext.tab.Panel');
        this.add(this.panel);

        this.tabs = new Array();
        
        return this;
    },
    
    addTab: function(comp){
    	this.panel.add(comp);
    	this.tabs.push(comp);
    },
    
    setActiveTab: function(comp){
    	this.panel.setActiveTab(comp);
    },

    loadData: function(data, operation){
    	Ext.each(this.tabs, function(tab){
    		tab.loadRecord(data);
    	});
    },
    
    showErrorMessage: function(data, operation){
        alert("It was impossible to retrieve the data!");
    },
    
    showMask: function(){
    	if(Ext.isEmpty(this.mask))
    		this.mask = new Ext.LoadMask(this, {msg:"Please wait..."});
        this.mask.show();
    },
    
    hideMask: function(){
    	if(!Ext.isEmpty(this.mask)) this.mask.hide();
   	}
});