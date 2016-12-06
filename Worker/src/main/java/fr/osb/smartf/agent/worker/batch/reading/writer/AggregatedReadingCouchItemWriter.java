package fr.osb.smartf.agent.worker.batch.reading.writer;


import fr.osb.smartf.agent.worker.couchbase.model.AggregatedCouchReadings;
import fr.osb.smartf.agent.worker.couchbase.model.KeyValue;
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

public class AggregatedReadingCouchItemWriter implements ItemWriter<AggregatedReadingDTO>, InitializingBean {

    Logger LOG = LoggerFactory.getLogger(AggregatedReadingMongoItemWriter.class);

    CouchbaseTemplate couchbaseTemplate;

    public AggregatedReadingCouchItemWriter(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @Override
    public void write(List<? extends AggregatedReadingDTO> items) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (AggregatedReadingDTO aggregatedReadingDTO : items) {
            AggregatedCouchReadings aggregatedReadings = new AggregatedCouchReadings();
            String guid = aggregatedReadingDTO.getIdPdc() + ".consumption";
            aggregatedReadings.setId(guid);
            aggregatedReadings.setDoctype("consumption");
            aggregatedReadings.setGuid(guid);
            aggregatedReadings.setPdcId(aggregatedReadingDTO.getIdPdc());
            List<KeyValue> consumption = new ArrayList<>();

            aggregatedReadingDTO.getReadings().forEach(readingPdc -> {
                KeyValue keyValue = new KeyValue();
                keyValue.setKey(readingPdc.getDate().format(formatter));
                keyValue.setValue(readingPdc.getConsumption());
                consumption.add(keyValue);
            });

            aggregatedReadings.setConsumption(consumption);

            couchbaseTemplate.save(aggregatedReadings);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(couchbaseTemplate != null, "CouchbaseTemplate is required.");
    }

}
