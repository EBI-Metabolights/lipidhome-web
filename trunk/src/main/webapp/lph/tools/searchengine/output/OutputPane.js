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

        this.hierarchy = Ext.create('lph.tools.searchengine.output.SelectionHierarchyPanel',{
            region	: 'west',
            width	: 300,
        });
        this.add(this.hierarchy);

        this.resultGrid = Ext.create('lph.tools.searchengine.output.ResultGrid',{
            region	: 'center',
            store   : this._getStore()
        });
        this.add(this.resultGrid);

        //This fixes a bug found on the column redimension (delete it as soon as EXTjs fixes it)
        //this.resultGrid.getStore().addListener('datachanged', Ext.bind(this.resultGrid._refresh,this.resultGrid));

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
            item.delta = Ext.util.Format.round(item.mass - item.resMass, 5);
        })
        if(this.fireEvent('beforeload')!==false){
            this.resultGrid._refresh();
            this.resultGrid.getStore().loadRawData(res);
            this.fireEvent('load');
        }

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
    url : '',
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
    ],
    proxy: {
        type: 'ajax',
        reader: {
            type: 'json',
            root: 'list'
        }
    }
});