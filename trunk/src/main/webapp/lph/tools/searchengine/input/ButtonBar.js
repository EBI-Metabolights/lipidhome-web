Ext.define('lph.tools.searchengine.input.ButtonBar', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    border  : false,
    frame   : true,
    layout  : {
        type    : 'hbox',
        pack    : 'end',
        align   : 'middle'
    },

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	submit  : true,
            reset   : true
        });

        this.reset = Ext.create('Ext.Button',{
            margin  : 2,
            width   : 100,
            text    : 'Reset',
            handler : Ext.bind(this.reset, this)
        });
        this.add(this.reset);

        this.submit = Ext.create('Ext.Button',{
            margin  : 2,
            width   : 100,
            text    : 'Submit',
            handler : Ext.bind(this.submit, this)
        });
        this.add(this.submit);

        return this;
    },

    submit: function(btn, event){
        this.fireEvent('submit');
    },

    reset: function(btn, event){
        this.fireEvent('reset');
    }
});