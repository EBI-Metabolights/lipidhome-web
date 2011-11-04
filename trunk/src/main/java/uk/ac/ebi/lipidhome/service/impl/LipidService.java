package uk.ac.ebi.lipidhome.service.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ebi.lipidhome.core.dao.DaoFactory;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.ResultStreamingOutput;

@Service
abstract class LipidService {

	@Autowired
	private DaoFactory daoFactory;

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	private Response getResponse(Result result, ResultStreamingOutput output){
		int statusCode = result.isSuccess() ? 200 : 500;
		return Response.status(statusCode).type("application/json").entity(output).build();
	}
	
	protected Response result2Response(Result result){
		return getResponse(result, new ResultStreamingOutput(result));
	}
	
	protected Response result2Response(Result result, String callback){
		return getResponse(result, new ResultStreamingOutput(result, callback));
	}
}
