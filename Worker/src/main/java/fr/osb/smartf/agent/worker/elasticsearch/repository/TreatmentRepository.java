package fr.osb.smartf.agent.worker.elasticsearch.repository;

import fr.osb.smartf.agent.worker.elasticsearch.model.Treatment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mpaltanea on 14.09.2016.
 */
@Repository
public interface TreatmentRepository extends ElasticsearchRepository<Treatment, String> {
}
