package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubClassService interface defines the avilable category related services and the paths that they map to.
 *
 */
@Path( "/" )
public abstract interface SubClassService {

	@GET
    @Path( "/summary" )
    Response getSubClassSummary( @QueryParam("id") Long id);
	
	@GET
    @Path( "/species" )
    Response getSpecieSimpleList( @QueryParam("id") Long id);
}
