Ext.define('lph.tools.searchengine.input.MassInputPanel', {
	/* Begin Definitions */
    extend	: 'Ext.form.FormPanel',
   
    region	: 'center',
    layout  : 'fit',
    padding : '10 10 10 10',
    frame   : true,

    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        //TODO: Rewrite the regexp (does NOT work on opera)
        Ext.form.VTypes['massesVal'] = /^(\d+\.\d+\n?)+$/;
        Ext.form.VTypes['massesText'] = "Input is not a list of new line separated decimal masses";
        Ext.form.VTypes['masses'] = function(v) {
            return Ext.form.VTypes['massesVal'].test(v);
        };


        this.textArea = Ext.create('Ext.form.field.TextArea',{
            name        : 'masses',
            allowBlank  : false,
            labelAlign  : 'top',
            fieldLabel  : 'List of Precursor Ion Masses',
            emptyText	: 'Paste in here your list of Precursor Ion Masses...',
            vtype       : 'masses'
        });
        //this.textArea.setValue("496.4773\n520.4013\n522.3777\n524.4802\n675.6826\n676.6461\n689.6807\n701.6923\n703.6888\n704.6431\n705.6451\n706.6284\n717.7772\n718.5522\n729.7747\n730.7498\n731.7684\n732.6449\n733.7463\n734.6209\n735.7308\n742.7662\n744.6719\n746.6152\n756.7594\n758.7122\n759.7445\n760.6337\n766.7591\n768.6900\n770.7202\n772.6564\n773.7426\n774.6504\n780.7138\n782.7121\n783.7496\n784.6519\n786.7512\n787.6513\n788.7312\n790.7499\n792.7021\n794.7070\n796.7246\n799.8526\n800.7506\n801.7788\n802.7420\n806.7228\n807.6497\n808.6316\n809.6462\n810.7530\n811.7457\n812.8498\n813.7723\n814.7467\n815.7260\n832.7626\n834.7557\n835.6513\n836.5514\n");
        this.textArea.setValue("496.4773\n520.4013\n522.3777\n524.4802\n675.6826\n676.6461\n689.6807\n701.6923\n703.6888\n704.6431\n705.6451\n706.6284\n717.7772\n718.5522\n729.7747\n730.7498\n731.7684\n732.6449\n733.7463\n734.6209\n735.7308\n");
        this.add(this.textArea);


        return this;
    },

    validate: function(){
        return this.textArea.validate();
    },

    getData: function(){
        return {
            'masses'  : this.textArea.getValue()
        }
    }
});