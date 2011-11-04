/*
 * 
 */
 
 /**
  * 
  */
Ext.define('lph.browser.nav.HierarchyPane', {
	/* Begin Definitions */
    extend	: 'Ext.tree.Panel',
    
    region	: 'center',
    layout	: 'fit',
    title	: 'Lipid structure hierarchy',
    border	: false,
    margins	: '0 5 0 5',
    frame	: true,
    rootVisible	: false,
	useArrows	: true,

	addNode: function(view, record, item, index, event, options, type, id){
		var sm = this.getSelectionModel();
		var selection = sm.getSelection();
		var selected = selection.pop();
		
		//First is necessary to check if the node already exists
		var itemId;
		var name;
		try{
			itemId= record.get("itemId");
			name = record.get("name");
		}catch(e){
			itemId = record.itemId;
			name = record.name;
		}
		
		var node = selected.findChild("itemId", itemId);
		//If the node does not exist, a new one is created and added
		if(Ext.isEmpty(node)){
			node = selected.appendChild(this._createNode(itemId, name, type));
			this._sortChildren(selected);
		}

		//The new node (or existing one) has to be selected, so an event
		//will be thrown and the event manager will execute the proper action
    	sm.select(node,false);
	},
	
	/**
	 * addNodeTo add a node to the parent
	 * 
	 * @param {} parent node to add the child
	 * @param {} itemId the id for the new child node 
	 * @param {} name the name for the new child node
	 * @param {} type the type of the new child node
	 * @return {} the added (or existing) child node 
	 */
	addNodeTo: function(parent, itemId, name, type){
		var node = parent.findChild("itemId", itemId);
		if(Ext.isEmpty(node)){
			node = parent.appendChild(this._createNode(itemId, name, type));
			this._sortChildren(parent);
		}
		return node;
	},
	
	_sortChildren: function(node){
		node.sort(function(n1, n2){
			return n1.get('text').localeCompare(n2.get('text'));
		});
	},
	
	/**
	 * 
	 * @param {} itemId
	 * @param {} name
	 * @param {} type
	 * @return {}
	 */
	_createNode: function(itemId, name, type){
		return {
			itemId	: itemId,
    		text	: name,
    		expanded: true,
    		type	: type
		}
	}
});