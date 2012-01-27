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


        this.opts = Ext.create('lph.documentation.HierarchyPane');
        this.add(this.opts);

        this.doc = Ext.create('Ext.panel.Panel',{
            region  : 'center'
        });

        this.opts.addListener('optionSelected', this._loadURL, this);

        this.add(this.doc);

        return this;
    },

    _loadURL: function(path){
        Ext.Ajax.request({
            url     : path,
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