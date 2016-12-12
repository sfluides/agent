package fr.osb.smartf.agent.worker.batch.reading.processor;

import fr.osb.smartf.agent.worker.couchbase.model.ReadingUhf;
import fr.osb.smartf.agent.worker.mongo.model.Reading;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTOMapper;
import fr.osb.smartf.agent.worker.service.dto.ReadingUhfDTO;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by mpaltanea on 08.12.2016.
 */
public class ReadingUhfItemProcessor implements ItemProcessor<ReadingUhfDTO, ReadingUhf> {

    @Override
    public ReadingUhf process(ReadingUhfDTO readingUhfDTO) throws Exception {
        return ReadingDTOMapper.toReadingUhf(readingUhfDTO);
    }
}
