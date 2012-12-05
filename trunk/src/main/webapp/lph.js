/*
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * This is the starting point for the web application, all Panes are added into this one.
 * The two main panes are currently the BrowserPane and the ToolsPane.
*/

var lph = {
    name    : 'LipidHome',
    version : '1.0',
    phase   : {
        html : '',
        text : ''
    },
	ws      : 'lphws'
};

Ext.application({
	name : 'Pathway Browser 2.0',
	launch : function() {
		lph.browser = Ext.create('lph.browser.BrowserPane');
		lph.tools = Ext.create('lph.tools.ToolsPane');
        lph.documentation = Ext.create('lph.documentation.DocumentationPane');

        var phase = (Ext.isEmpty(lph.phase) || Ext.isEmpty(lph.phase.html)) ? "" : lph.phase.html;
		lph.viewport = Ext.create('Ext.container.Viewport', {
			layout	: "border",
			id		: 'maintab',
			items : [{
				region	: 'north',
                //padding : 5,
                border  : false,
                html    : '<div class="banner">' +
                            '<div style="float: left; margin-left: 15px;"><img src="resources/images/LipidHomeBanner.png" alt="LipidHome" height="60"/></div>' +
                            '<div style="float: left;"><span style="color: #990000"><b>v'+ lph.version +'</b> ' + phase + '</span></div>' +
                            '<div class="banner-text">' +
                                '<b>LipidHome</b> is a portal of lipidomics knowledge, encompassing a structure browser, tools and accessible web services.' +
                            '</div>' +
                          '</div>'

			},{
				region      : 'center',
				xtype       : 'tabpanel',
                minTabWidth : 80,
                tabWidth    : 95,
                autoWidth   : true,
                autoHeight  : true,
				activeTab   : 0,
				items : [ lph.browser, lph.tools, lph.documentation ]
			}]
		});

        lph.tools.addListener('itemSelected', lph.browser.manageLocationItemSelected, lph.browser);
	}
});

Ext.onReady(function() {
	Ext.QuickTips.init();
    Ext.Loader.setPath('Ext.ux', 'extjs/examples/ux/');
    Ext.require([
        'Ext.ux.RowExpander',
        'Ext.ux.grid.FiltersFeature'
    ]);

    var title = Ext.get("web-title");
    if(!Ext.isEmpty(title)){
        var phase = (Ext.isEmpty(lph.phase) || Ext.isEmpty(lph.phase.html)) ? "" : "(" + lph.phase.html + ")";
        title.update(lph.name + " :: v" + lph.version + " " + phase );
    }

    var remove = function() {
		Ext.get('loading').remove();
		Ext.get('loading-mask').fadeOut({
					remove : true
				});
	};
	setTimeout(remove, 250);
});