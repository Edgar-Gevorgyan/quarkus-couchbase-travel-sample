package org.egevorgyan.controller;

import lombok.AllArgsConstructor;
import org.egevorgyan.model.AirlineEntity;
import org.egevorgyan.service.AirlineService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@AllArgsConstructor
@Path("/airlines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirlineController {

    private AirlineService airlineService;

    @POST
    public Response addAirline(AirlineEntity airlineEntity) {
        AirlineEntity response = airlineService.createAirline(airlineEntity);
        return Response.ok(response).status(201).build();
    }

    @GET
    public List<AirlineEntity> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GET
    @Path("/{id}")
    public AirlineEntity getAirline(@PathParam("id") long id) {
        return airlineService.getAirline(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateAirline(@PathParam("id") long id, AirlineEntity airlineEntity) {
        AirlineEntity response = airlineService.updateAirline(id, airlineEntity);
        return Response.ok(response).status(201).build();
    }
}