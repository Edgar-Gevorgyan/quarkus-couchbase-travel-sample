package org.egevorgyan.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.egevorgyan.model.AirlineEntity;
import org.egevorgyan.service.AirlineService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;

@AllArgsConstructor
@Path("/airlines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirlineController {

    private AirlineService airlineService;

    @POST
    public Uni<Response> addAirline(AirlineEntity airlineEntity) {
        long id = airlineEntity.getId();
        return airlineService.createAirline(id, airlineEntity)
                .onItem()
                .transform(response -> Response.ok(response).status(CREATED).build())
                .onFailure()
                .recoverWithItem(Response.status(BAD_REQUEST).entity("The airline with id: " + id + " already exist").build());
    }

    @GET
    public Multi<AirlineEntity> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getAirline(@PathParam("id") long id) {
        return airlineService.getAirline(id)
                .onItem()
                .transform(response -> Response.ok(response).build())
                .onFailure()
                .recoverWithItem(Response.status(NOT_FOUND).entity("The airline with id: " + id + " doesn't exist").build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updateAirline(@PathParam("id") long id, AirlineEntity airlineEntity) {
        return airlineService.updateAirline(id, airlineEntity)
                .onItem()
                .transform(response -> Response.ok(response).status(CREATED).build())
                .onFailure()
                .recoverWithItem(Response.status(NOT_FOUND).entity("The airline with id: " + id + " doesn't exist").build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteAirline(@PathParam("id") long id) {
        return airlineService.deleteAirline(id)
                .onItem()
                .transform(response -> Response.status(NO_CONTENT).build())
                .onFailure()
                .recoverWithItem(Response.status(NOT_FOUND).entity("The airline with id: " + id + " doesn't exist").build());
    }
}