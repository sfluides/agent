package fr.osb.smartf.agent.worker.couchbase.repository;

import fr.osb.smartf.agent.worker.couchbase.model.CouchReading;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mpaltanea on 13.06.2016.
 */
@Repository("couchReadingRepository")
public interface CouchReadingRepository extends CouchbaseRepository<CouchReading, String> {
}
