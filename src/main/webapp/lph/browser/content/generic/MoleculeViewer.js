/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 * This class creates  CehemDoodle molecule View and applies the appropriate graphical parameters to it so the image
 * is in keeping with the standardised way of representing molecules (in our case a cross between CHEBI's style and the
 * nomenclature described by the LIPID MAPS consortium)
 */

 //@todo The H atoms on sn2 glycerol carbon must be rendered in black not white, so they are not confused with methyl.
Ext.define('lph.browser.content.generic.MoleculeViewer', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
	autoEl	: {tag: 'canvas'},
    border	: false,
    
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);
        
        this.addListener('resize', this.createViewerCanvas, this);
        
        return this;
    },
    
    renderMolecule: function(molfile){
    	this.mol = ChemDoodle.readMOL(molfile);
    	this.createViewerCanvas();
    },
    
    createViewerCanvas: function(){
    	if(Ext.isEmpty(this.mol)) return;
    	
    	var h = this.getHeight();
    	var w = this.getWidth();
    	this.viewerCanvas = new ChemDoodle.ViewerCanvas(this.id, w, h);
    	this._customizeViewerCanvas(this.viewerCanvas);
    	this.viewerCanvas.loadMolecule(this.mol);	
    },
    
    _customizeViewerCanvas: function(viewer){
    	if(Ext.isEmpty(viewer)) return;
    	
    	var standardH = 150.0;
    	var h = this.getHeight();

    	//the width of the bonds should be .6 pixels for the standard height
    	var BONDS_WIDH_2D = h * 0.6 / standardH;
    	//the hashed wedge spacing should be 2.5 pixels for the standard height
    	var BONDS_HASH_SPACING = h * 2.5 / standardH;
		//the atom label font size should be 10  for the standard height
    	var ATOMS_FONT_SIZE = h * 8 / standardH;
    	//the bond lengths should be 14.4 pixels for the standard height
    	var SCALE_TO_AVERAGE_BOND_LENGTH = h * 14.4/ standardH;

    	//console.info(h, 'BONDS_WIDH_2D', BONDS_WIDH_2D, 'BONDS_HASH_SPACING', BONDS_HASH_SPACING, 'ATOMS_FONT_SIZE', ATOMS_FONT_SIZE, 'SCALE_TO_AVERAGE_BOND_LENGTH', SCALE_TO_AVERAGE_BOND_LENGTH);
    	
		viewer.specs.bonds_width_2D = BONDS_WIDH_2D;
		//the spacing between higher order bond lines should be 18% of the length of the bond
		viewer.specs.bonds_saturationWidth_2D = .18;
		viewer.specs.bonds_hashSpacing_2D = BONDS_HASH_SPACING;
		viewer.specs.atoms_font_size_2D = ATOMS_FONT_SIZE;
		//we define a cascade of acceptable font families
		//if Helvetica is not found, Arial will be used
		viewer.specs.atoms_font_families_2D = ['Helvetica', 'Arial', 'sans-serif'];
		//display carbons labels if they are terminal
		viewer.specs.atoms_displayTerminalCarbonLabels_2D = true;
		//add some color by using JMol colors for elements
		viewer.specs.atoms_useJMOLColors = true;
		
		//viewer.specs.bonds_atomLabelBuffer_2D = .35;
		
		this.mol.scaleToAverageBondLength(SCALE_TO_AVERAGE_BOND_LENGTH);
    }
});