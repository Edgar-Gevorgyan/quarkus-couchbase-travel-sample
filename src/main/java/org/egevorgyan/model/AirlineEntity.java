package org.egevorgyan.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AirlineEntity {
    private long id;
    private String type;
    private String name;
    private String iata;
    private String icao;
    private String callsign;
    private String country;
}
