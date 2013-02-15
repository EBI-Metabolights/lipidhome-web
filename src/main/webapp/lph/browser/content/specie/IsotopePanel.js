/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * This Panel contains the isotope distribution for the given lipid species
 */
Ext.define('lph.browser.content.specie.IsotopePanel', {
    /* Begin Definitions */
    extend:'Ext.grid.Panel',

    title:'Isotopes',
    iconCls:'isotopes-16',
    border:false,
    autoScroll:true,
    collapsible:true,
    animCollapse:false,
    disabled:true,

    columns:[
        {header:'Mass', dataIndex:'mass'},
        {header:'Intensity', dataIndex:'intensity', flex:1}
    ],

    loadRecord:function (data) {
        var records = data.isotopes().getNewRecords();
        this.store.add(records);
        this._updateTab(records.length);
    },

    _updateTab:function (num) {
        this.setTitle(this.title + " (" + num + ")");
        if (num > 0) this.enable();
    }
});