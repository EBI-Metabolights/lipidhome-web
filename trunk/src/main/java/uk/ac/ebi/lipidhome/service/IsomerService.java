package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@Path( "/" )
public abstract interface IsomerService {
    @GET
    @Path( "/summary" )
    Response getIsomerSummary( @QueryParam("id") Long id);
}
