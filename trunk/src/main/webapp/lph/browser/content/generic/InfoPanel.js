Ext.define('lph.browser.content.generic.InfoPanel', {
	/* Begin Definitions */
    extend	: 'Ext.Panel',
    
    loadRecord: function(data){
    	if(!Ext.isEmpty(this.content))
    		this.content.loadRecord(data);
    		
    	if(!Ext.isEmpty(this.moleculeViewer))
    		this.moleculeViewer.renderMolecule(data.get('model'));
    }
});