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
