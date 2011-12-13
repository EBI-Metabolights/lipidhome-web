/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 * The SubSpeciePane displays all the information relevant to categories. It comprises two parts, the InfoPanel which
 * gives some statistics on lower hierarchy members of this category, and the MainClassList which is a list of its
 * direct children in the hierarchy.
 */

Ext.define('lph.browser.content.welcome.WelcomePane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    frame   : true,
    border	: false,
    autoScroll : true,
    html    : "Hello",

    url 	: 'resources/static/browser/welcome/welcome.html',

    constructor: function(config){
    	this.callParent(arguments);
        this.initConfig(config);

        this._loadURL();

        return this;
    },

    _loadURL: function(){
        if(Ext.isEmpty(this.url)) return;

        Ext.Ajax.request({
            url     : this.url,
            success : function(response,opts) {
                this.update(response.responseText);
            },
            scope   : this
        });
    }
});