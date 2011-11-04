package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface SubClassService {

	@GET
    @Path( "/summary" )
    Response getSubClassSummary( @QueryParam("id") Long id);
	
	@GET
    @Path( "/species" )
    Response getSpecieSimpleList( @QueryParam("id") Long id);
}
