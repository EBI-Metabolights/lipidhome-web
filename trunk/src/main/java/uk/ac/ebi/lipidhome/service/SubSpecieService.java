package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface SubSpecieService {

	@GET
    @Path( "/summary" )
    Response getSubSpecieSummary( @QueryParam("id") Long id);
	
	@GET
    @Path( "/isomers" )
    Response getIsomerSimpleList( @QueryParam("id") Long id);
}