Ext.define('lph.tools.searchengine.output.OutputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    title	: 'Output',
    layout	: 'border',

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
           load         : true,
           error        : true,
           itemSelected : true
        });


        var store = Ext.create('Ext.data.TreeStore', {
            fields  : [
                {name: 'itemId',  type : 'int'},
                {name: 'code',  type : 'string'},
                {name: 'type',  type : 'string'}//, defaultValue: 'category'}
            ]
		});
        this.hierarchy = Ext.create('lph.tools.searchengine.output.SelectionHierarchyPanel',{
            region	: 'west',
            width	: 300,
            store   : store
        });
        //this.hierarchy.getChecked();
        this.add(this.hierarchy);

        var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
            enableGroupingMenu  : false,
            groupHeaderTpl      : 'Mass: {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})'
        })
        var sm = Ext.create('Ext.selection.CheckboxModel',{
            checkOnly: true
        });
        sm.suspendEvents();
        this.resultGrid = Ext.create('lph.tools.searchengine.output.ResultGrid',{
            region	    : 'center',
            store       : this._getStore(),
            selModel    : sm,
            features	: [
                groupingFeature,
                {
                    ftype : 'filters',
                    local : true
                }
            ],
        });
        this.add(this.resultGrid);

        return this;
    },

    executeQuery : function(params){
        Ext.Ajax.request({
            method  : 'POST',
            url     : 'service/tools/ms1search',
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
		    model       : 'MS1SearchEngineResultModel',
            groupField  : 'mass'
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