package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path( "/" )
public abstract interface FAScanSpecieService {

	@GET
    @Path( "/summary" )
    Response getFAScanSpecieSummary( @QueryParam("id") Long id);
	
	@GET
    @Path( "/subspecies" )
    Response getSubSpecieSimpleList( @QueryParam("id") Long id);
}