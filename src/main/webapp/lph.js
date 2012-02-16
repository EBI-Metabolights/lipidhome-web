/*
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * This is the starting point for the web application, all Panes are added into this one.
 * The two main panes are currently the BrowserPane and the ToolsPane.
 * @TODO This is where history management will be completed in the future.
*/


// Needed if you want to handle history for multiple components in the same page.
// Should be something that won't be in component ids.
var tokenDelimiter = '|';

// -- --
var lph = {
    name    : 'LipidHome',
    version : '1.0',
    phase   : {
        html : '&beta;eta',
        text : 'beta'
    },
	ws      : 'lphws'
};

Ext.Loader.setPath('Ext.ux', 'extjs/examples/ux/');
Ext.require([
    'Ext.ux.RowExpander',
    'Ext.ux.grid.FiltersFeature'
]);

/**
 * 
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @date July 2011
 */
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

    var title = Ext.get("web-title");
    if(!Ext.isEmpty(title)){
        var phase = (Ext.isEmpty(lph.phase) || Ext.isEmpty(lph.phase.html)) ? "" : "(" + lph.phase.html + ")";
        title.update(lph.name + " :: v" + lph.version + " " + phase );
    }

	var historyManagement = function(token){
		/*
		console.info("token",token);
		var content = lph.browser.content;
		if(token){
	        var parts = token.split(tokenDelimiter);
	        
	        var record = {itemId: parts[1], name: "No Joe No"};
	        var type = parts[0];
	                
	        var hierarchy = lph.browser.navigator.hierarchy;
	        hierarchy.addNode(null, record, null, null, null, null, type, null);
	        
	        //content.loadContent(null, [selection], null);
	        //content.show();
	        //content.setActiveTab(tabId);
	    }else{
	        // This is the initial default state.  Necessary if you navigate starting from the
	        // page without any existing history token params and go back to the start state.
	        content.setActiveTab(0);
	        content.getItem(0).setActiveTab(0);
	    }
	    */
	};
	
	//TODO: Add here the management for loading the correct content pane tab in function of the URI
	
	// The only requirement for this to work is that you must have a hidden field and
    // an iframe available in the page with ids corresponding to Ext.History.fieldId
    // and Ext.History.iframeId.  See history.html for an example.
	Ext.require('Ext.util.History', function(){
		Ext.History.init();	
		
		// Handle this change event in order to restore the UI to the appropriate history state
	    Ext.History.on('change', historyManagement);
	});
	
	var remove = function() {
		Ext.get('loading').remove();
		Ext.get('loading-mask').fadeOut({
					remove : true
				});
	};
	setTimeout(remove, 250);
	
	//console.info('The version of ChemDoodle installed is: '+ ChemDoodle.getVersion());
});