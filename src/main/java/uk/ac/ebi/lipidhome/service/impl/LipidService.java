/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *  LipidService is the core Class that most Services extend, it gives them access to the DaoFactory and methods
 *  to transform Results into json encoded Responses,
 */

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

    /**
     *
     * @return The DAOFactory
     */
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

    /**
     *
     * @param daoFactory The DaoFactor to be set as accessible to all services extending LipidService
     */
	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

    /**
     *
     * @param result A ResultObject, usually some form of ResultObject.
     * @param output The ResultSteramingOutput to write to.
     * @return A json encoded Response object of the Result
     */
	private Response getResponse(Result result, ResultStreamingOutput output){
		int statusCode = result.isSuccess() ? 200 : 500;
		return Response.status(statusCode).type("application/json").entity(output).build();
	}

    /**
     * This is a convenience function for getResponse
     * @param result A json encoded Response object of the Result
     * @return
     */
	protected Response result2Response(Result result){
		return getResponse(result, new ResultStreamingOutput(result));
	}
	
	protected Response result2Response(Result result, String callback){
		return getResponse(result, new ResultStreamingOutput(result, callback));
	}
}
