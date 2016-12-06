package fr.osb.smartf.agent.worker.batch.reading.writer;

import fr.osb.smartf.agent.worker.mongo.model.Reading;
import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import fr.osb.smartf.agent.worker.service.dto.AggregatedReadingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by szagoret on 27.06.2016.
 */
public class AggregatedReadingMongoItemWriter implements ItemWriter<AggregatedReadingDTO>, InitializingBean {

    Logger LOG = LoggerFactory.getLogger(AggregatedReadingMongoItemWriter.class);


    MongoTemplate mongoTemplate;

    @Override
    public void write(List<? extends AggregatedReadingDTO> items) throws Exception {
        LOG.info("Start writing a collection of " + items.size() + " elements");
        for (AggregatedReadingDTO aggregatedReadingDTO : items) {
            List<ReadingPdc> readings = aggregatedReadingDTO.getReadings();
            readings.forEach((reading) -> {
                        Query findAndUpdateQuery = new Query(Criteria.where("_id").is(reading.getId()));
                        Update updateQuery = Update.update("cons", reading.getConsumption());
                        mongoTemplate.updateFirst(findAndUpdateQuery, updateQuery, Reading.class);
                    }
            );
        }
        LOG.info("End writing a collection");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(mongoTemplate != null, "A MongoOperations implementation is required.");
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
