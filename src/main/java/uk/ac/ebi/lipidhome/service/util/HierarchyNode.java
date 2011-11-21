package uk.ac.ebi.lipidhome.service.util;

import uk.ac.ebi.lipidhome.core.model.LipidType;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectListOfLists;

import java.util.ArrayList;
import java.util.List;

/**
 * A hierarchy node is a simple class holding the minimal information required to display it in the tree
 * and a list of its children which are also hierarchy nodes.
 */
public class HierarchyNode {
	private Long itemId;
	private String name;
	private String type;
	private boolean identified;
	private List<HierarchyNode> children = new ArrayList<HierarchyNode>();

	public HierarchyNode(Long itemId, String name, boolean identified, String type){
		this.itemId = itemId;
		this.name = name;
		this.identified = identified;
		this.type = type;
	}

    /**
     *
     * @param bsi Also constructable from a base search item.
     */
	public HierarchyNode(BaseSearchItem bsi){
		this.itemId = bsi.getItemId();
		this.name = bsi.getName();
		this.identified = bsi.getIdentified();
		this.type = bsi.getType();
	}

	public Long getItemId() {
		return itemId;
	}

	public String getName(){
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean getIdentified(){
		return identified;
	}

	public void addChild(HierarchyNode node){
		children.add(node);
	}

	public void addChild(BaseSearchItem bsi){
		children.add(new HierarchyNode(bsi));
	}

	public void addChildren(List<BaseSearchItem> children){
		for(BaseSearchItem item : children){
			this.children.add(new HierarchyNode(item));
		}
	}

	public List<HierarchyNode> getChildren(){
		return children;
	}

	public List<HierarchyNode> getChildren(LipidType type){
		List<HierarchyNode> children = new ArrayList<HierarchyNode>();
		if(type.isType(this.type)){
			children.add(this);
		}else{
			for(HierarchyNode child : this.children){
				children.addAll(child.getChildren(type));
			}
		}
		return children;
	}


	public ResultObjectListOfLists toFlatLists(){
		ResultObjectListOfLists result = new ResultObjectListOfLists();

		BaseSearchItem bsi = new BaseSearchItem(itemId, name, identified, type);

		if(children.isEmpty()){
			ResultObjectList path = new ResultObjectList();
			path.add(bsi);
			result.add(path);
		}else{
			for(HierarchyNode child : children){
				ResultObjectListOfLists paths = child.toFlatLists();
				for(ResultObjectList auxPath : paths){
					auxPath.add(bsi);
					result.add(auxPath);
				}
			}
		}

		return result;
	}
}