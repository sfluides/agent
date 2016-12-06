package fr.osb.smartf.agent.worker.batch.reading.writer;

import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by szagoret on 17.06.2016.
 */
public class MongoTemplateReadingItemWriter<T> implements ItemWriter<T>, InitializingBean {

    MongoTemplate mongoTemplate;

    @Override
    public void write(List<? extends T> items) throws Exception {
        for (Object o : items) {
            ReadingPdc reading = (ReadingPdc) o;
            mongoTemplate.upsert(new Query(Criteria.where("_id").is(reading.getId())),
                    new Update()
                            .set("idPdc", reading.getIdPdc())
                            .set("date", reading.getDate())
                            .set("index", reading.getIndex()),
                    ReadingPdc.class);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(mongoTemplate != null, "A reference to MongoTemplate implementation is required.");
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
