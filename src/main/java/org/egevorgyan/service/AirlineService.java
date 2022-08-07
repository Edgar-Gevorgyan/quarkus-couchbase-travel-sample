package org.egevorgyan.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.egevorgyan.model.AirlineEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AirlineService {

    final Cluster cluster = Cluster.connect("127.0.0.1", "couchbase", "couchbase");
    final Bucket bucket = cluster.bucket("travel-sample");
    final Collection collection = bucket.defaultCollection();

    public AirlineEntity createAirline(AirlineEntity airlineEntity) {
        collection.insert(getDocumentId(airlineEntity.getId()), airlineEntity);
        return airlineEntity;
    }

    public List<AirlineEntity> getAllAirlines() {
        return cluster.query("select airline.* from `travel-sample`.inventory.airline")
                .rowsAs(AirlineEntity.class);
    }

    public AirlineEntity getAirline(long id) {
        return collection.get(getDocumentId(id)).contentAs(AirlineEntity.class);
    }

    public AirlineEntity updateAirline(long id, AirlineEntity airlineEntity) {
        airlineEntity.setId(id);
        collection.replace(getDocumentId(id), airlineEntity);
        return airlineEntity;
    }

    private static String getDocumentId(long id) {
        return "airline_" + id;
    }
}
