package org.egevorgyan.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.egevorgyan.config.CouchbaseConfig;
import org.egevorgyan.model.AirlineEntity;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AirlineService {

    private final CouchbaseConfig couchbaseConfig;

    private Cluster cluster;
    private Collection collection;

    public AirlineService(CouchbaseConfig couchbaseConfig) {
        this.couchbaseConfig = couchbaseConfig;
    }

    @PostConstruct
    void initialize() {
        cluster = Cluster.connect(
                couchbaseConfig.host(),
                couchbaseConfig.username(),
                couchbaseConfig.password()
        );
        Bucket bucket = cluster.bucket(couchbaseConfig.bucketName());
        collection = bucket.defaultCollection();
    }


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
