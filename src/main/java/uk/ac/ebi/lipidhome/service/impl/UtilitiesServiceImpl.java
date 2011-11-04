package uk.ac.ebi.lipidhome.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.dao.MainClassDao;
import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.dao.SubClassDao;
import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.LipidType;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;
import uk.ac.ebi.lipidhome.service.UtilitiesService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.ResultObject;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectListOfLists;


public class UtilitiesServiceImpl extends LipidService implements UtilitiesService{

	public UtilitiesServiceImpl(){
		
	}

	@Override
	public Response doSearch(String query, Integer type, Long start, Long limit, Long page, String callback) {
		Result result;
		SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
		
		try {
			List<BaseSearchItem> list = subSpecieDao.getSubSpeciesByNameLike(query, start, limit);
			System.out.println(list.size());
			ListConverter<BaseSearchItem> converter = new ListConverter<BaseSearchItem>();
			result = new Result(converter.getLipidObjectList(list));
			result.setTotalCount(subSpecieDao.getSubSpeciesCountByNameLike(query));
		} catch (RuntimeException e) {
			String errorMessage = "It was impossible to search by '" + query + "'.";
			result = new Result(errorMessage);
		}
		
		return result2Response(result, callback);
	}

	@Override
	public Response getPathsTo(Long itemId, String name, Boolean identified, String type) {
		BaseSearchItem bsi = new BaseSearchItem(itemId, name, identified, type); 
		HierarchyNode tree = new HierarchyNode(bsi);
		
		switch(LipidType.getType(type)){
		case ISOMER:
			for(HierarchyNode isomer : tree.getChildren(LipidType.ISOMER)){
				
			}
		case SUB_SPECIE:
			System.out.println("subSpecie");
			for(HierarchyNode subSpecie : tree.getChildren(LipidType.SUB_SPECIE)){
				SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
				subSpecie.addChildren(subSpecieDao.getSubSpecieParents(subSpecie.getItemId()));
			}
		case FA_SCAN_SPECIE:
			System.out.println("faScanSpecie");
			for(HierarchyNode faScanSpecie : tree.getChildren(LipidType.FA_SCAN_SPECIE)){
				System.out.println(faScanSpecie.getItemId());
				FAScanSpecieDao<FAScanSpecie> fassDao = getDaoFactory().getFAScanSpecieDao();
				faScanSpecie.addChildren(fassDao.getFAScanSpecieParents(faScanSpecie.getItemId()));
			}
		case SPECIE:
			for(HierarchyNode specie : tree.getChildren(LipidType.SPECIE)){
				SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
				specie.addChildren(specieDao.getSpecieParents(specie.getItemId()));
			}
		case SUB_CLASS:
			for(HierarchyNode subClass : tree.getChildren(LipidType.SUB_CLASS)){
				SubClassDao<SubClass> subClassDao = getDaoFactory().getSubClassDao();
				subClass.addChildren(subClassDao.getSubClassParents(subClass.getItemId()));
			}
		case MAIN_CLASS:
			for(HierarchyNode mainClass : tree.getChildren(LipidType.MAIN_CLASS)){
				MainClassDao<MainClass> mainClassDao = getDaoFactory().getMainClassDao();
				mainClass.addChildren(mainClassDao.getMainClassParents(mainClass.getItemId()));
			}
			break;
			
		case NONE:
			//throw exception JOE
		}
		
		Result result = new Result(tree.toFlatLists());
		return result2Response(result);
	}
	
}


class HierarchyNode {
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
	
	/**
	 * 
	 * @param type
	 * @return
	 */
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






class ResultNode <T extends ResultObject> {
	
	private T node;
	
	private List<ResultNode<T>> children = new ArrayList<ResultNode<T>>();
	
	public ResultNode(T node){
		this.node = node;
	}
	
	public T getNode(){
		return node;
	}
	
	public void addChild(ResultNode<T> node){
		children.add(node);
	}
	
	public List<ResultNode<T>> getChildren(){
		return children;
	}
	
	public boolean isLeaf(){
		return children.isEmpty();
	}
	
	public ResultObjectListOfLists toFlatLists(){
		ResultObjectListOfLists result = new ResultObjectListOfLists();
		
		if(this.isLeaf()){
			ResultObjectList path = new ResultObjectList(); 
			path.add(node);
			result.add(path);
		}else{
			for(ResultNode<T> child : children){
				ResultObjectListOfLists paths = child.toFlatLists();
				for(ResultObjectList auxPath : paths){
					auxPath.add(node);
					result.add(auxPath);
				}
			}
		}
		
		return result;
	}
}

class ResultTree <T extends ResultObject> extends ResultNode<T>{

	public ResultTree(T node) {
		super(node);
	}

}