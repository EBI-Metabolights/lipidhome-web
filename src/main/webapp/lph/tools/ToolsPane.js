/**
 * This panel holds the tabs of the various tools associated with the database.
 */
Ext.define('lph.tools.ToolsPane', {
	/* Begin Definitions */
    extend		: 'Ext.tab.Panel',

    tabConfig   : {
        title    : 'Tools',
        tooltip  : 'A selection of lipidomics tools'
    },
    iconCls     : 'tools-16',
	tabPosition	: 'bottom',

	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	itemSelected : true
        });

        this.searchEngine = Ext.create('lph.tools.searchengine.SearchEnginePane');
        this.add(this.searchEngine);
        
        this.add({
        	title	: 'Other tool'
        	//,disabled: true
        })
        
        this.setActiveTab(this.searchEngine);

        this.bind();

        return this;
	},

    bind: function(){
        this.searchEngine.output.resultGrid.addListener('itemdblclick', this._ms1SearchItemSelected, this);
    },

    _ms1SearchItemSelected: function(grid, record, item, index, event, opts){
        this.fireEvent('itemSelected', record);
    }
});