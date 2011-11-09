/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The MainClassService interface defines the avilable category related services and the paths that they map to.
 */

package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface MainClassService {

	@GET
    @Path( "/summary" )
    Response getMainClassSummary( @QueryParam("id") Long id);
	
	@GET
    @Path( "/subclasses" )
    Response getSubClassSimpleList( @QueryParam("id") Long id);
}
