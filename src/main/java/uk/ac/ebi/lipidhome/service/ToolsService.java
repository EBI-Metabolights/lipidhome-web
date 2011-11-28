package uk.ac.ebi.lipidhome.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path( "/" )
public interface ToolsService {

    @POST
    @Path( "/ms1search" )
    Response ms1Search(@FormParam("masses") String masses, @FormParam("level") String level, @FormParam("tolerance") Float tolerance, @FormParam("identified") Boolean identified);

}
