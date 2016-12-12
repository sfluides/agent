package fr.osb.smartf.agent.worker.service.dto;

import fr.osb.smartf.agent.worker.couchbase.model.ReadingUhf;
import fr.osb.smartf.agent.worker.mongo.model.Reading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by mpaltanea on 08.06.2016.
 */
public class ReadingDTOMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ReadingDTOMapper.class);

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

    /**
     * Mapping ReadingUhfDTO to ReadingUhf entity
     *
     * @param readingUhfDTO
     * @return
     */
    public static ReadingUhf toReadingUhf(ReadingUhfDTO readingUhfDTO) {

        // TODO make all fields of DTO string format and convert it there
        ReadingUhf readingUhf = new ReadingUhf();

        readingUhf.setId(readingUhfDTO.getId().replace("\"", ""));
        LOG.warn(readingUhfDTO.getId());

        try {
            Long unixTimestamp = Math.round((Double.parseDouble(readingUhfDTO.getDate()) - 25569) * 86400);
            readingUhf.setDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp), ZoneId.systemDefault()));
        } catch (NumberFormatException nfex) {
            LOG.error("could not parse date for UHF reading " + readingUhfDTO.getId());
        }

        readingUhf.setDoctype(readingUhfDTO.getDoctype());
        try {
            readingUhf.setIndex(Double.parseDouble(readingUhfDTO.getIndex()));
        } catch (NumberFormatException nfex) {
            LOG.error("could not parse index from UHF reading");
        }
        readingUhf.setGuid(readingUhfDTO.getGuid().replace("\"", ""));
        readingUhf.setPdcId(readingUhfDTO.getPdcId());
        readingUhf.setUhfAlarmeFuite(readingUhfDTO.getUhfAlarmeFuite());

        return readingUhf;
    }
}
