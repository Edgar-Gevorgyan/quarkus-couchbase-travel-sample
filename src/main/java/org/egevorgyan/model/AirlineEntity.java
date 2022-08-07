package org.egevorgyan.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineEntity {
    private long id;
    private String type;
    private String name;
    private String iata;
    private String icao;
    private String callsign;
    private String country;
}
