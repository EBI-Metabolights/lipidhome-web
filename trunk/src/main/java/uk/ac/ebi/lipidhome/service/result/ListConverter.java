package uk.ac.ebi.lipidhome.service.result;

import java.util.List;

import uk.ac.ebi.lipidhome.service.result.model.ResultObject;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectListOfLists;

public class ListConverter<T extends ResultObject> {

	public ResultObjectList getLipidObjectList(List<T> list){
		ResultObjectList tmp = new ResultObjectList(); 
		for (T item : list) {
			tmp.add(item);
		}
		return tmp;
	}
	
	/*
	public ResultObjectListOfLists getLipidObjectsListOfLists(List<List<T>> list){
		ResultObjectListOfLists tmp = new ResultObjectListOfLists();
		for (List<T> listAux : list) {
			ResultObjectList rolAux = new ResultObjectList();
			for (T item : listAux) {
				rolAux.add(item);
			}
			tmp.add(rolAux);
		}
		return tmp;
	}
	*/
}
