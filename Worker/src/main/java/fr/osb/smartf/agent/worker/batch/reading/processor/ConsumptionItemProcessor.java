package fr.osb.smartf.agent.worker.batch.reading.processor;

import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import fr.osb.smartf.agent.worker.service.dto.AggregatedReadingDTO;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by szagoret on 30.06.2016.
 */
public class ConsumptionItemProcessor implements ItemProcessor {

//    @Autowired
//    private ReadingRepository mongoReadingRepository;

    @Override
    public Object process(Object item) throws Exception {
        AggregatedReadingDTO aggregatedReadingDTO = (AggregatedReadingDTO) item;


//        Optional<Reading> lastReading = Optional.ofNullable(mongoReadingRepository.
//                findFirst1ByIdPdcAndConsIsNotNullOrderByDateDesc(aggregatedReadingDTO.getIdPdc()));
        List<ReadingPdc> sortedByDateReadings = aggregatedReadingDTO.getReadings().stream()
                .sorted(Comparator.comparing(ReadingPdc::getDate)).collect(Collectors.toList());
//        if (lastReading.isPresent()) {
//            referenceValue = lastReading.get().getIndex();
//        }
        Double referenceValue = 0.0;
        for (ReadingPdc reading : sortedByDateReadings) {
            if (reading.getIndex() != null) {
                Double consumption = reading.getIndex() - referenceValue;
                reading.setConsumption(new BigDecimal(consumption).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                referenceValue = reading.getIndex();
            }
        }

        //TODO change this
        //add random consumption value for first index
        if(sortedByDateReadings.size() > 0) {
            sortedByDateReadings.get(0).setConsumption(sortedByDateReadings.get(sortedByDateReadings.size() - 1).getConsumption());
        }
        aggregatedReadingDTO.setReadings(sortedByDateReadings);

        return aggregatedReadingDTO;
    }
}
