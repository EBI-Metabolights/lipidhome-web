Ext.define('lph.tools.searchengine.input.InputPane', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',

    title	: 'Input',
    layout	: 'border',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
           query  : true
        });

        this.massInput = Ext.create('lph.tools.searchengine.input.MassInputPanel',{
            region	: 'center'
        });
        this.add(this.massInput);

        this.opts = Ext.create('lph.tools.searchengine.input.OptionsPane',{
            region	: 'east'
        });
        this.add(this.opts);

        this.buttons = Ext.create('lph.tools.searchengine.input.ButtonBar',{
            region  : 'south'
        });
        this.add(this.buttons);

        this.buttons.addListener('submit', this.submit, this);
        this.buttons.addListener('reset', this.reset, this);

        return this;
    },

    submit: function(){
        var massInputValid = this.massInput.validate();
        var optsValid = this.opts.validate();
        if( massInputValid && optsValid){
            var data = Ext.merge(this.massInput.getData(), this.opts.getData());
            this.fireEvent('query', data);
        }else{
            Ext.Msg.show({
                 title:'Data Validation Error',
                 msg: 'There was an error validating some part of the input data or search parameters.',
                 buttons: Ext.Msg.OK,
                 icon: Ext.Msg.ERROR
            });
        }
    },

    reset: function(){
        console.info("Reset");
    },
});