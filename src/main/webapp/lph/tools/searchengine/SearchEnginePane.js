/**
 * This is the Search engine tab and contains the two major Panels for its use, input and output.
 */
Ext.define('lph.tools.searchengine.SearchEnginePane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Search engine',
    iconCls : 'ms1-search-engine-16',
    layout  : 'border',
    
    constructor : function(config){
    	this.callParent(arguments);
    	this.initConfig(config);

        this.addEvents({
        	itemSelected : true
        });

        var accordion = Ext.create('Ext.panel.Panel',{
            layout	: 'accordion',
            region  : 'center'
        })

        this.input = Ext.create('lph.tools.searchengine.input.InputPane');
        accordion.add(this.input);
        
        
        this.output = Ext.create('lph.tools.searchengine.output.OutputPane');
	    accordion.add(this.output);

        this.add(accordion);

        this.description = Ext.create('lph.tools.generic.DescriptionPanel',{
			collapsed	: true,
            region		: 'east',
            width		: 300,
	        url 		: 'resources/static/tools/ms1searchengine/description.html',
        });
        this.add(this.description);

        this.bind();

        return this;
    },

    bind: function(){
        this.input.addListener('query', this._executeQuery, this);
        this.output.addListener('beforeload', this.expandOutput, this);
        this.output.addListener('beforeload', this.unmask, this);
        this.output.addListener('error', this.queryError, this);
    },

    _executeQuery: function(data){
        this.mask();
        this.output.executeQuery(data)
    },

    mask: function(){
        this.searchMask = new Ext.LoadMask(this.getEl(), {msg:"Querying <b>LipidHome</b>, please wait..."});
        this.searchMask.show();
    },

    unmask: function(){
        if(!Ext.isEmpty(this.searchMask))
            this.searchMask.hide();
    },

    expandOutput: function(){
        this.output.expand();
    },

    queryError: function(error){
        if(!Ext.isEmpty(this.searchMask))
            this.searchMask.hide();

        Ext.Msg.show({
             title:'Server Error',
             msg: error.errorMessage,
             buttons: Ext.Msg.OK,
             icon: Ext.Msg.ERROR
        });
    }
});