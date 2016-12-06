package fr.osb.smartf.agent.worker.service.dto;

import fr.osb.smartf.agent.worker.mongo.model.Reading;

/**
 * Created by mpaltanea on 08.06.2016.
 */
public class ReadingDTOMapper {

    /**
     * Mapping ReadingDTO to Reading entity
     *
     * @param readingDTO
     * @return
     */
    public static Reading toReading(ReadingDTO readingDTO) {

        // TODO make all fields of DTO string format and convert it there
        Reading reading = new Reading();

        reading.setId(readingDTO.getId());
        reading.setDate(readingDTO.getDate());
        reading.setIdPdc(readingDTO.getIdPdc());
        reading.setIndex(readingDTO.getIndex());
        reading.setQmin(readingDTO.getQmin());
        reading.setCons(readingDTO.getCons());
//
        return reading;
    }
}
