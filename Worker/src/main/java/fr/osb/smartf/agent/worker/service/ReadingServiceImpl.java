package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.mongo.model.Reading;
import fr.osb.smartf.agent.worker.mongo.repository.ReadingRepository;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTO;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by mpaltanea on 08.06.2016.
 */
@Service
public class ReadingServiceImpl implements ReadingService {
    private static final Logger LOG = LoggerFactory.getLogger(ReadingServiceImpl.class);

    @Autowired
    private ReadingRepository mongoReadingRepository;

    /*@Autowired
    private CouchReadingRepository couchReadingRepository;*/

    @Override
    public void save(List<ReadingDTO> readingDTOList) {

        if (!readingDTOList.isEmpty()) {
            long startTime = System.currentTimeMillis();
            List<Reading> readingList = readingDTOList.stream().map(ReadingDTOMapper::toReading).collect(toList());
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            LOG.info("Execution time: " + totalTime + " ms");

            startTime = System.currentTimeMillis();
            mongoReadingRepository.save(readingList);
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            LOG.info("Execution insert time: " + totalTime + " ms");

        }
    }

    @Override
    public void calculateConsumption() {
        LOG.info("Start select all cons null");
        List<Reading> readings = mongoReadingRepository.findByConsIsNull();
        LOG.info("End select all cons null");
        readings.stream();


//        Map<String, List<Reading>> readingsMapByIdPdc
//                = readings.stream()
//                  .sorted((r1, r2) -> r1.getDate().compareTo(r2.getDate()))
//                  .collect(Collectors.groupingBy(Reading::getIdPdc));
        LOG.info("Group elements by pdc id");
//        readingsMapByIdPdc.forEach((idPdc, readingsByIdPdc) -> {
//            Optional<Reading> lastReading = Optional.ofNullable(mongoReadingRepository.
//                    findFirst1ByIdPdcAndConsIsNotNullOrderByDateDesc(idPdc));
//            calculateConsumptionByPdc(readingsByIdPdc, lastReading);
//        });

        LOG.info("End calcul conso");
    }

    public void calculateConsumptionByPdc(List<Reading> readings, Optional<Reading> lastReading) {

        double referenceValue = 0;
        if (lastReading.isPresent()) {
//            referenceValue = Double.parseDouble(lastReading.get().getIndex());
        }
//        List<CouchReading> couchReadings = new ArrayList<CouchReading>();
//
//        for (Reading reading: readings) {
//            if (reading.getIndex()!=null) {
//                CouchReading couchReading = new CouchReading();
//                // TODO generate ID
//                couchReading.setId((new Random().nextLong()));
//                couchReading.setDate(reading.getDate());
////                couchReading.setIdPdc(reading.getIdPdc());
//                couchReading.setQmin(reading.getQmin());
////                couchReading.setIndex(reading.getIndex());
////                couchReading.setCons( (Double.parseDouble(reading.getIndex()) - referenceValue) + "");
//                couchReadings.add(couchReading);
//
////                reading.setCons( (Double.parseDouble(reading.getIndex()) - referenceValue) + "");
////                referenceValue = Double.parseDouble(reading.getIndex());
//            }
//        }

        mongoReadingRepository.save(readings);
//        couchReadingRepository.save(couchReadings);
    }
}
