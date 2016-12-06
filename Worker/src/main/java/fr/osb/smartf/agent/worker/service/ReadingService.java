package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.mongo.model.Reading;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by mpaltanea on 08.06.2016.
 */
public interface ReadingService {
    void save(List<ReadingDTO> readingDTOList);
    void calculateConsumption();
    void calculateConsumptionByPdc(List<Reading> readings, Optional<Reading> lastReading);
}
