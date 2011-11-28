Ext.define('lph.tools.searchengine.output.OutputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Output',
    layout	: 'border',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
           load : true,
           error: true
        });

        this.hierarchy = Ext.create('lph.tools.searchengine.output.SelectionHierarchyPanel');
        this.add(this.hierarchy);

        this.resultGridStore = this._getStore();
        this.result = Ext.create('lph.tools.searchengine.output.ResultGrid',{
            region  : 'center',
            store   : this.resultGridStore
        });
        this.add(this.result);

        return this;
    },

    executeQuery : function(params){
        Ext.Ajax.request({
            method  : 'POST',
            url     : '/service/tools/ms1search',
            params  : params,
            success : this.onSuccess,
            failure : this.onFailure,
            scope   : this
        });
    },

    onSuccess: function(res, opts){
        var res = Ext.decode(res.responseText);
        Ext.each(res.list, function(item){
            item.delta = item.mass - item.resMass;
        })
        this.resultGridStore.loadData(res.list);
        this.fireEvent('load');
        this.expand(true);
    },

    onFailure: function(res, opts){
        var res = Ext.decode(res.responseText);
        this.fireEvent('error', res);
    },

    _getStore: function(id){
    	return Ext.create('Ext.data.Store', {
		    model: 'MS1SearchEngineResultModel'
		});
    }
});

Ext.define('MS1SearchEngineResultModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'itemId', type: 'int' },
        { name: 'name', type: 'string' },
        { name: 'mass', type: 'float' },
        { name: 'identified', type: 'boolean' },
        { name: 'faCarbons', type: 'int' },
        { name: 'faDoubleBonds', type: 'int' },
        { name: 'resMass', type: 'float' },
        { name: 'delta', type: 'float'},
        { name: 'code', type: 'string' },
        { name: 'type', type: 'string' }
    ]
});