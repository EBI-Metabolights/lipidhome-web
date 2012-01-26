Ext.define('lph.documentation.DocumentationPane', {
	/* Begin Definitions */
    extend		: 'Ext.panel.Panel',

    tabConfig   : {
        title    : 'Documentation',
        tooltip  : 'Documentation and help about LipidHome'
    },
    iconCls     : 'help-16',
    layout      : 'border',
    mainFolder  : 'resources/static/documentation/',    //Always ending with "/"

	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        var accordion = Ext.create('Ext.panel.Panel',{
            layout	    : 'accordion',
            title       : 'Options',
            width       : 300,
            maxWidth    : 300,
            minWidth    : 200,
            region      : 'west',
            collapsible : true,
            split		: true
        })

        var browser = Ext.create('Ext.panel.Panel',{
            title   : 'Browser',
            iconCls : 'home-16',
        });
        this._getFolderContent("browser", browser);

        var tools = Ext.create('Ext.panel.Panel',{
            title   : 'MS1 Search Engine',
            iconCls : 'ms1-search-engine-16' //'tools-16'
        });
        this._getFolderContent("tools/ms1searchengine", tools);

        var webServices = Ext.create('Ext.panel.Panel',{
            title   : 'Web Services',
            iconCls : 'ws-16',
        });
        this._getFolderContent("webservices", webServices);

        accordion.add([browser, tools, webServices]);

        this.doc = Ext.create('Ext.panel.Panel',{
            region  : 'center'
        });

        this.add(accordion);
        this.add(this.doc);

        return this;
    },

    _loadOptions: function(panel, paths){
        var items = [];
        Ext.Array.each(paths, function(path){
            var elems = path.split("\/");
            var file = elems[elems.length-1];
            var name = file.split(".")[0].replace(/_/g," ");
            items.push({
                text    : Ext.String.capitalize(name),
                url     : path,
                iconCls : 'document-16',
                listeners: {
                    click: this._loadURL,
                    scope: this
                }
            });
        },this);
        if(items.length==0) return;
        var menu = Ext.create('Ext.menu.Menu', {
            floating: false,  // usually you want this set to True (default)
            plain   : true,
            defaults: {minWidth: 292},
            items   : items
        });
        panel.add(menu);
    },

    _getFolderContent: function(folder, panel){
        var url = this.mainFolder + folder;
        //var regx =  new RegExp(url + "/[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)?", "g"); // All files
        var regx =  new RegExp(url + "\/[a-zA-Z0-9_]+\.html", "g");   //Only HTML
        Ext.Ajax.request({
            url     : url,
            success : function(response,opts) {
                this._loadOptions(panel, response.responseText.match(regx));
            },
            scope   : this
        });
    },

    _loadURL: function(btn, event, opts){
        if(Ext.isEmpty(btn.url)) return;

        Ext.Ajax.request({
            url     : btn.url,
            success : function(response,opts) {
                this.doc.update(response.responseText);
            },
            failure : function(response,opts) {
                this.doc.update("Documentation not available, please contact web administrator.");
            },
            scope   : this
        });
    }
});