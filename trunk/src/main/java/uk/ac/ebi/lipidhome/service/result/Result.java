package uk.ac.ebi.lipidhome.service.result;

import uk.ac.ebi.lipidhome.service.result.model.ResultObject;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectListOfLists;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * This class is a generic holder of lots of different result types fro the different services
 *
 */
public class Result {

	private boolean success;
	
	private ResultObject data;
	
	private ResultObjectList list;
	
	private ResultObjectListOfLists paths;
	
	private String errorMessage;
	
	private Long totalCount;

	public Result(ResultObject data) {
		super();
		success = true;
		this.data = data;
	}

	public Result(String errorMessage) {
		super();
		success = false;
		this.errorMessage = errorMessage;
	}
	
	public Result(ResultObjectList list){
		super();
		success = true;
		this.list = list;
	}
	
	public Result(ResultObjectListOfLists paths){
		super();
		success = true;
		this.paths = paths;
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultObject getData() {
		return data;
	}
	
	public ResultObjectList getList() {
		return list;
	}
	
	public ResultObjectListOfLists getPaths() {
		return paths;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
