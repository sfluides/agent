package fr.osb.smartf.agent.worker.batch.reading.processor;

import fr.osb.smartf.agent.worker.mongo.model.Reading;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTO;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTOMapper;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by szagoret on 19.07.2016.
 */
public class ReadingItemProcessor implements ItemProcessor<ReadingDTO, Reading> {

    @Override
    public Reading process(ReadingDTO readingDTO) throws Exception {
        return ReadingDTOMapper.toReading(readingDTO);
    }
}
