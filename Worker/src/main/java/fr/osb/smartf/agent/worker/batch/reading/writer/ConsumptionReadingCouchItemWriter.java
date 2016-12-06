package fr.osb.smartf.agent.worker.batch.reading.writer;

import fr.osb.smartf.agent.worker.couchbase.model.CouchReading;
import fr.osb.smartf.agent.worker.service.dto.AggregatedReadingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.util.Assert;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szagoret on 12.10.2016.
 */
public class ConsumptionReadingCouchItemWriter implements ItemWriter<AggregatedReadingDTO>, InitializingBean {

    Logger LOG = LoggerFactory.getLogger(ConsumptionReadingCouchItemWriter.class);

    CouchbaseTemplate couchbaseTemplate;


    public ConsumptionReadingCouchItemWriter(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @Override
    public void write(List<? extends AggregatedReadingDTO> items) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (AggregatedReadingDTO aggregatedReadingDTO : items) {

            List<CouchReading> collectionToSave = new ArrayList<>();

            aggregatedReadingDTO.getReadings().forEach(readingPdc -> {
                CouchReading couchReading = new CouchReading();
                couchReading.setId(readingPdc.getId());
                couchReading.setIdPdc(readingPdc.getIdPdc());

                couchReading.setDate(readingPdc.getDate().format(formatter));
                couchReading.setIndex(readingPdc.getIndex().toString());

                couchReading.setConsumption(readingPdc.getConsumption().doubleValue());
                collectionToSave.add(couchReading);
            });

            couchbaseTemplate.save(collectionToSave);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(couchbaseTemplate != null, "CouchbaseTemplate is required.");
    }
}
