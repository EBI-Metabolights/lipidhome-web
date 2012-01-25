Ext.define('lph.documentation.DocumentationPane', {
	/* Begin Definitions */
    extend		: 'Ext.panel.Panel',

    tabConfig   : {
        title    : 'Documentation',
        tooltip  : 'Documentation and help about LipidHome'
    },
    iconCls     : 'help-16',
    layout      : 'border',

	constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        var accordion = Ext.create('Ext.panel.Panel',{
            //layout	: 'accordion',
            title   : 'Options',
            width   : 300,
            region  : 'west'
        })

        this.doc = Ext.create('Ext.panel.Panel',{
            region  : 'center'
        });

        this.add(accordion);
        this.add(this.doc);

        return this;
    }
});