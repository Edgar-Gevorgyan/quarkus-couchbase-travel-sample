package org.egevorgyan.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.egevorgyan.model.AirlineEntity;
import org.egevorgyan.service.AirlineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.*;

@ExtendWith(MockitoExtension.class)
class AirlineControllerTest {

    @Mock
    AirlineService airlineService;
    @InjectMocks
    AirlineController underTest;

    @Test
    void should_succeed_addAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.createAirline(expected.getId(), expected)).thenReturn(Uni.createFrom().item(expected));

        Response actual = underTest.addAirline(expected).await().indefinitely();

        Assertions.assertEquals(CREATED.getStatusCode(), actual.getStatus());
        Assertions.assertEquals(expected, actual.getEntity());
    }

    @Test
    void should_fail_addAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.createAirline(expected.getId(), expected)).thenReturn(Uni.createFrom().failure(new Exception()));

        Response actual = underTest.addAirline(expected).await().indefinitely();

        Assertions.assertEquals(BAD_REQUEST.getStatusCode(), actual.getStatus());
        Assertions.assertEquals("The airline with id: " + expected.getId() + " already exist", actual.getEntity());
    }

    @Test
    void should_succeed_getAllAirlines() {

        List<AirlineEntity> expected = List.of(
                new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States")
        );

        Mockito.when(airlineService.getAllAirlines()).thenReturn(Multi.createFrom().iterable(expected));

        var actual = underTest.getAllAirlines().subscribe().asStream().toArray();

        Assertions.assertArrayEquals(expected.toArray(), actual);
    }

    @Test
    void should_succeed_getAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.getAirline(expected.getId())).thenReturn(Uni.createFrom().item(expected));

        Response actual = underTest.getAirline(expected.getId()).await().indefinitely();

        Assertions.assertEquals(OK.getStatusCode(), actual.getStatus());
        Assertions.assertEquals(expected, actual.getEntity());
    }

    @Test
    void should_fail_getAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.getAirline(expected.getId())).thenReturn(Uni.createFrom().failure(new Exception()));

        Response actual = underTest.getAirline(expected.getId()).await().indefinitely();

        Assertions.assertEquals(NOT_FOUND.getStatusCode(), actual.getStatus());
        Assertions.assertEquals("The airline with id: " + expected.getId() + " doesn't exist", actual.getEntity());
    }

    @Test
    void should_succeed_updateAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.updateAirline(expected.getId(), expected)).thenReturn(Uni.createFrom().item(expected));

        Response actual = underTest.updateAirline(expected.getId(), expected).await().indefinitely();

        Assertions.assertEquals(CREATED.getStatusCode(), actual.getStatus());
        Assertions.assertEquals(expected, actual.getEntity());
    }

    @Test
    void should_fail_updateAirline() {

        AirlineEntity expected = new AirlineEntity(1, "airline", "American Airlines", "AA", "AAL", "AMERICAN", "United States");

        Mockito.when(airlineService.updateAirline(expected.getId(), expected)).thenReturn(Uni.createFrom().failure(new Exception()));

        Response actual = underTest.updateAirline(expected.getId(), expected).await().indefinitely();

        Assertions.assertEquals(NOT_FOUND.getStatusCode(), actual.getStatus());
        Assertions.assertEquals("The airline with id: " + expected.getId() + " doesn't exist", actual.getEntity());
    }

    @Test
    void should_succeed_deleteAirline() {

        long id = 1;

        Mockito.when(airlineService.deleteAirline(id)).thenReturn(Uni.createFrom().item(true));

        Response actual = underTest.deleteAirline(id).await().indefinitely();

        Assertions.assertEquals(NO_CONTENT.getStatusCode(), actual.getStatus());
    }

    @Test
    void should_fail_deleteAirline() {

        long id = 1;

        Mockito.when(airlineService.deleteAirline(id)).thenReturn(Uni.createFrom().failure(new Exception()));

        Response actual = underTest.deleteAirline(id).await().indefinitely();

        Assertions.assertEquals(NOT_FOUND.getStatusCode(), actual.getStatus());
        Assertions.assertEquals("The airline with id: " + id + " doesn't exist", actual.getEntity());
    }
}