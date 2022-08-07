package org.egevorgyan.controller;

import lombok.AllArgsConstructor;
import org.egevorgyan.model.AirlineEntity;
import org.egevorgyan.service.AirlineService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@AllArgsConstructor
@Path("/airlines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirlineController {

    private AirlineService airlineService;

    @POST
    public Response addAirline(AirlineEntity airlineEntity) {
        long id = airlineEntity.getId();
        Optional<AirlineEntity> response = airlineService.createAirline(id, airlineEntity);
        if(response.isPresent()) {
            return Response.ok(response.get()).status(201).build();
        }
        return Response.status(BAD_REQUEST).entity("The airline with id: " + id + " already exist").build();
    }

    @GET
    public List<AirlineEntity> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GET
    @Path("/{id}")
    public Response getAirline(@PathParam("id") long id) {
        Optional<AirlineEntity> airlineEntity = airlineService.getAirline(id);
        if(airlineEntity.isPresent()) {
            return Response.ok(airlineEntity.get()).build();
        }
        return Response.status(NOT_FOUND).entity("The airline with id: " + id + " doesn't exist").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAirline(@PathParam("id") long id, AirlineEntity airlineEntity) {
        Optional<AirlineEntity> response = airlineService.updateAirline(id, airlineEntity);
        if(response.isPresent()) {
            return Response.ok(response.get()).status(201).build();
        }
        return Response.status(NOT_FOUND).entity("The airline with id: " + id + " doesn't exist").build();
    }
}