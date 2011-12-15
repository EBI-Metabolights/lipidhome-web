/**
 * The content manager is responsible for creating or returning existing tabs from the provided values.
 */
Ext.define('lph.browser.content.ContentManager', {
	/* Begin Definitions */
    extend	: 'Ext.util.Observable',
    
	/** Define a HashMap <type , panes> where panes is another HashMap with <id, pane> */
	typePanes : null,
	
	constructor: function(config){
		this.callParent(arguments);
        this.initConfig(config);
        
        this.typePanes = new Ext.util.HashMap();
        
        this.addEvents({
        	newContent : true
        });

        return this;
    },
    
    getPanel: function(type, id, parentId, params){
    	var panel = this._getPanel(type, id, parentId);
        if(!Ext.isEmpty(panel)){
            return panel;
        }
    	
    	if(type=="category"){
	    	panel = Ext.create('lph.browser.content.category.CategoryPane', {
	        	itemId	: id,
	        	type	: type,
                parentId: parentId,
	        	node	: params.node,
	        	elem	: params.elem
	        })
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'mainClass', itemId: id});

    	}else if(type=="mainClass"){
    		panel = Ext.create('lph.browser.content.mainclass.MainClassPane', {
	        	itemId	: id,
	        	type	: type,
	        	parentId: parentId,
                node	: params.node,
	        	elem	: params.elem
	        });
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'subClass', itemId: id});
    	}else if(type=="subClass"){
    		panel = Ext.create('lph.browser.content.subclass.SubClassPane', {
	        	itemId	: id,
	        	type	: type,
	        	parentId: parentId,
                node	: params.node,
	        	elem	: params.elem
	        });
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'specie', itemId: id});
    	}else if(type=="specie"){
    		panel = Ext.create('lph.browser.content.specie.SpeciePane', {
	        	itemId	: id,
	        	type	: type,
	        	parentId: parentId,
                node	: params.node,
	        	elem	: params.elem
	        });
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'faScanSpecie', itemId: id});
    	}else if(type=="faScanSpecie"){
    		panel = Ext.create('lph.browser.content.fascanspecie.FAScanSpeciePane', {
	        	itemId	: id,
	        	type	: type,
	        	parentId: parentId,
                node	: params.node,
	        	elem	: params.elem
	        });
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'subSpecie', itemId: id});
    	}else if(type=="subSpecie"){
    		panel = Ext.create('lph.browser.content.subspecie.SubSpeciePane', {
	        	itemId	: id,
	        	type	: type,
	        	parentId: parentId,
                node	: params.node,
	        	elem	: params.elem
	        });
	        this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'isomer', itemId: id});
    	}else if(type=="isomer"){
    		panel = Ext.create('Ext.Panel');
	        //this.fireEvent('newContent', {list : panel.list, path: panel.details.path, type : 'fasSpecie', itemId: id});
    	}
    	
    	this._addPanel(type, id, parentId, panel);

    	return panel;
    },
    
    _getOrCreate: function(type){
    	var hash = this.typePanes.get(type);
    	
    	if(Ext.isEmpty(hash)){
    		hash = new Ext.util.HashMap();
    		this.typePanes.add(type,hash);
    	}
    	
    	return hash;
    },
    
    _addPanel: function(type, id, parentId, panel){
    	var hash = this._getOrCreate(type);
        var paneRef = this._getPanelReference(id, parentId);
    	if(hash.containsKey(paneRef)){
            //TODO: Thrown an exception
            if (Ext.global.console) {
                Ext.global.console.warn("The panel already exists");
            }
    	}else{
    		hash.add(paneRef, panel);
    	}
    },
    
    _getPanel: function(type, id, parentId){
    	if(this.typePanes.containsKey(type)){
            var paneRef = this._getPanelReference(id, parentId);
    		return this.typePanes.get(type).get(paneRef);
    	}else{
    		return undefined;
    	}
    },

    _getPanelReference: function(id, parentId){
        return id + "-" + parentId;
    }
});