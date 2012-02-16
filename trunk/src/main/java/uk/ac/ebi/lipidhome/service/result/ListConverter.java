package uk.ac.ebi.lipidhome.service.result;

import uk.ac.ebi.lipidhome.service.result.model.ResultObject;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * This class converts a list of objects into a single ResultObjectList object
 *
 */
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
