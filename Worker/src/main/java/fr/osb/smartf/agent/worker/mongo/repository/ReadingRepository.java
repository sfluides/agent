package fr.osb.smartf.agent.worker.mongo.repository;

import fr.osb.smartf.agent.worker.mongo.model.Reading;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mpaltanea on 09.06.2016.
 */
@Repository("readingRepository")
public interface ReadingRepository extends MongoRepository<Reading, String> {
    List<Reading> findByConsIsNull();
    Reading findFirst1ByIdPdcAndConsIsNotNullOrderByDateDesc(Integer idPdc);
}
