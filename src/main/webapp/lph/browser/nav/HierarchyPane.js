/*
    The HierarchyPane is a Tree that represents the structure of the items in the database.
    Items are either added, or if they already exist just selected.
    Upon adding new items the tree is reordered to always be in alphabetical order.

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

        this.selectNode({"node":node});
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

    selectNode: function(e){
        var sm = this.getSelectionModel();
		//The new node (or existing one) has to be selected, so an event
		//will be thrown and the event manager will execute the proper action
    	sm.select(e.node,false);
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
    },

    createPathsToNode: function(e){
        var auxChild;
        Ext.each(e.paths, function(path){
            var node = this.getRootNode();
            Ext.each(path, function(elem){
                node = this._addNode(node, elem);
            }, this);
            if(Ext.isEmpty(auxChild)) auxChild = node;
        },this);

        this.selectNode({"node":auxChild});
    },

    _addNode: function(parent, elem){
        var nodeAux;
        parent.eachChild(function(node){
            if(node.get("itemId")==elem.itemId){
                nodeAux = node;
            }
        }, this);

        if(Ext.isEmpty(nodeAux)){
            nodeAux = parent.appendChild(this._createNode(elem.itemId, elem.name, elem.type));
        }
        return nodeAux;
    }
});