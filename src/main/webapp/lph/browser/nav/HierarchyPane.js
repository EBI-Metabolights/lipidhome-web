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

	addNode: function(view, record, item, index, event, options, id, type){
		var sm = this.getSelectionModel();
		var selection = sm.getSelection();
		var selected = selection.pop();

		//First is necessary to check if the node already exists
		var itemId;
		var name;
        var identified;
		try{
			itemId= record.get("itemId");
			name = record.get("name");
            identified = record.get("identified");
		}catch(e){
			itemId = record.itemId;
			name = record.name;
            identified = record.identified;
		}
		
		var node = selected.findChild("itemId", itemId);
		//If the node does not exist, a new one is created and added
		if(Ext.isEmpty(node)){
			node = selected.appendChild(this._createNode(itemId, name, type, identified));
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
        //By default, only nodes expanded to the "selected" node if they were collapsed
        if(!e.node.isVisible()) e.node.collapse();
        this._expandPathFromRoot(e.node);

        var sm = this.getSelectionModel();
		//The new node (or existing one) has to be selected, so an event
		//will be thrown and the event manager will execute the proper action
    	sm.select(e.node,false);
    },

    _expandPathFromRoot: function(node){
        if(node!=this.getRootNode()){
            node.parentNode.expand();
            this._expandPathFromRoot(node.parentNode);
        }else{
            node.expand();
        }
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
	_createNode: function(itemId, name, type, identified){
        if(Ext.isEmpty(identified)){
            identified = true;
        }
		return {
			itemId	: itemId,
    		text	: name,
    		expanded: true,
    		type	: type,
            iconCls : type + this._identified2String(identified)
		}
    },

    _identified2String: function(identified){
        return identified ? "-identified" : "-unidentified";
    },

    createPathsToNode: function(paths){
        var auxChild;
        Ext.each(paths, function(path){
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
            nodeAux = parent.appendChild(this._createNode(elem.itemId, elem.name, elem.type, elem.identified));
        }
        return nodeAux;
    },

    findPathTo: function(record){
        Ext.Ajax.request({
            url     : '/service/utils/pathto',
            method  : 'GET',
            params  : {
                itemId      : record.get("itemId"),
                name        : record.get("name"),
                identified  : record.get("identified"),
                type        : record.get("type")
            },
            success : this.setPathToItems,
            scope   : this
        });

    },

    setPathToItems: function(response){
        var resp = Ext.decode(response.responseText);
        this.createPathsToNode(resp.paths);
    }
});