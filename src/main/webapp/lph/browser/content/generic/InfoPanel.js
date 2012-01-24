Ext.define('lph.browser.content.generic.InfoPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    iconCls : 'information-16',
    
    loadRecord: function(data){
        if(!Ext.isEmpty(this.name))
            data.set("name", this.name);

    	if(!Ext.isEmpty(this.content))
            this.content.loadRecord(data);

    	if(!Ext.isEmpty(this.moleculeViewer))
    		this.moleculeViewer.renderMolecule(data.get('model'));
    },

    constructor : function(config){
    	this.callParent(arguments);
        this.initConfig(config);

        this.addListener('render', this._onRender);

        return this;
    },

    _onRender: function(){
        this._enableTooltips();
    },

    _enableTooltips: function(){
        if(Ext.isEmpty(this.content) || Ext.isEmpty(this.content.items)) return;

        this.content.items.each(function(item){
            item.addListener('render', this._onTextFieldRender)
        }, this);
    },

    _onTextFieldRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;
        Ext.QuickTips.register({
            target  : c.getEl(),
            text    : c.tooltip,
            title   : c.fieldLabel,
            width   : 300
        });
    }
});