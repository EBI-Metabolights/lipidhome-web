/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
Ext.define('lph.tools.generic.ResultGrid', {
	/* Begin Definitions */
    extend	: 'Ext.grid.Panel',

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
        if(Ext.isEmpty(this.columns)) return;

        Ext.Array.each(this.columns, function(c){
            c.addListener('render', this._onColumnRender)
        }, this);
    },

    _onColumnRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;

        Ext.QuickTips.register({
            target  : c.getEl(),
            text    : c.tooltip,
            title   : c.text
        });
    }
});