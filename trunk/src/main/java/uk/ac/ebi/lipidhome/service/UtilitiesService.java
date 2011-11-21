/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The UtilitiesService interface is a collection of services and the paths they map to that do not relate to a single
 * structural hierachy level, but cross multiple of them.
 */

package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface UtilitiesService {

	@GET
    @Path( "/search" )
    Response doSearch( @QueryParam("query") String query,  @QueryParam("type") String type, @QueryParam("start") Long start, @QueryParam("limit") Long limit, @QueryParam("page") Long page, @QueryParam("callback") String callback);
	
	@GET
    @Path( "/pathto" )
    Response getPathsTo( @QueryParam("itemId") Long itemId, @QueryParam("name") String name, @QueryParam("identified") Boolean identified, @QueryParam("type") String type);
}
