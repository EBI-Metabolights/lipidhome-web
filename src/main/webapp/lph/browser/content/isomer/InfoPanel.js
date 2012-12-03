/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date October 2012
 *
 */
Ext.define('lph.browser.content.isomer.InfoPanel', {
    /* Begin Definitions */
    extend	: 'lph.browser.content.generic.InfoPanel',

    title	: 'Isomer info',
    layout	: 'border',
    border	: false,

    constructor : function(config){
        this.callParent(arguments);
        this.initConfig(config);

        this.moleculeViewer = Ext.create('lph.browser.content.generic.MoleculeViewer',{
            region	: 'center'
        })
        this.add(this.moleculeViewer);

        if(!Ext.isEmpty(this.smile)){
            this.moleculeViewer.addListener('afterrender', Ext.bind(this.moleculeViewer.renderSmile, this.moleculeViewer, [this.smile]));
        }

        return this;
    },

    loadRecord: function(data){
        this.smile = data.get("smile");
        this.moleculeViewer.renderSmile(this.smile);
    }//,

    /*
    get_aux_mol_file: function(){
        var mol = "\n";
        mol+="  CDK     1201111458\n";
        mol+="\n";
        mol+="23 22  0  0  0  0  0  0  0  0999 V2000\n";
        mol+="   2.1434    0.2221    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   2.8579   -0.1904    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   1.4289   -0.1904    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   3.5724    0.2221    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   0.7145    0.2221    0.0000 P   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   4.2868   -0.1904    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   3.5724    1.0471    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   4.2869    0.6346    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   0.7146    1.0471    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   0.7145   -0.6028    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="   0.0000   -0.1904    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -0.7145    0.2221    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -1.4289   -0.1904    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -2.1434    0.2221    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -2.8579   -0.1904    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -3.5724    0.2221    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -3.5721    1.0472    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -4.2868   -0.1904    0.0000 R1  0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -2.1433   -1.4278    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -1.4288   -1.0153    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -2.8578   -1.0153    0.0000 R2  0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -2.1433   -2.2527    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+="  -0.8455   -0.7738    0.0000 H   0  0  0  0  0  0  0  0  0  0  0  0\n";
        mol+=" 2  1  1  0  0  0  0\n";
        mol+=" 1  3  1  0  0  0  0\n";
        mol+=" 4  2  1  0  0  0  0\n";
        mol+=" 3  5  1  0  0  0  0\n";
        mol+=" 6  4  1  0  0  0  0\n";
        mol+=" 7  4  1  0  0  0  0\n";
        mol+=" 4  8  1  0  0  0  0\n";
        mol+=" 9  5  2  0  0  0  0\n";
        mol+=" 5 10  1  0  0  0  0\n";
        mol+=" 5 11  1  0  0  0  0\n";
        mol+="11 12  1  0  0  0  0\n";
        mol+="13 12  1  0  0  0  0\n";
        mol+="13 14  1  0  0  0  0\n";
        mol+="15 14  1  0  0  0  0\n";
        mol+="16 15  1  0  0  0  0\n";
        mol+="17 16  2  0  0  0  0\n";
        mol+="18 16  1  0  0  0  0\n";
        mol+="19 20  1  0  0  0  0\n";
        mol+="21 19  1  0  0  0  0\n";
        mol+="22 19  2  0  0  0  0\n";
        mol+="13 20  1  6  0  0  0\n";
        mol+="13 23  1  1  0  0  0\n";
        mol+="M  CHG  1   4   1\n";
        mol+="M  CHG  1  10  -1\n";
        mol+="M  RGP  2  18   1  21   2\n";
        mol+="M  END";
        return mol;
    }
    */
});
