package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface UtilitiesService {

	@GET
    @Path( "/search" )
    Response doSearch( @QueryParam("query") String query,  @QueryParam("type") Integer type, @QueryParam("start") Long start, @QueryParam("limit") Long limit, @QueryParam("page") Long page, @QueryParam("callback") String callback);
	
	@GET
    @Path( "/pathto" )
    Response getPathsTo( @QueryParam("itemId") Long itemId, @QueryParam("name") String name, @QueryParam("identified") Boolean identified, @QueryParam("type") String type);
}
