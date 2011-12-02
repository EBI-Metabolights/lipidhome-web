package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path( "/" )
public abstract interface ToolsService {
    @POST
    @Path( "/ms1search" )
    Response ms1Search(@FormParam("masses") String masses, @FormParam("level") String level, @FormParam("tolerance") Float tolerance, @FormParam("identified") Boolean identified);

    @POST
    @Produces("application/xml")
    @Path( "/export" )
    //Response ms1Export(@QueryParam("data") String data, @QueryParam("format") String format);
    Response ms1Export(@FormParam("data") String data, @FormParam("format") String format);
}
