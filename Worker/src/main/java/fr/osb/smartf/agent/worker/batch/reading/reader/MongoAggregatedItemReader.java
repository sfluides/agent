package fr.osb.smartf.agent.worker.batch.reading.reader;

import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import fr.osb.smartf.agent.worker.service.dto.AggregatedReadingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by szagoret on 22.06.2016.
 */
public class MongoAggregatedItemReader extends AbstractPagingItemReader implements InitializingBean {


    Logger LOG = LoggerFactory.getLogger(MongoAggregatedItemReader.class);

    private volatile String lastItem = "0";

    private MongoOperations template;
    private Class<AggregatedReadingDTO> type;

    public MongoAggregatedItemReader() {
        super();
        setName(ClassUtils.getShortName(MongoAggregatedItemReader.class));
    }


    @Override
    protected void doReadPage() {
        synchronized (this) {
            LOG.info("doReadPage() of " + getPageSize() + " elements where idPdc is greater than " + lastItem+" thread "+Thread.currentThread().getName());
            if (results != null && results.size() > 0) {
                lastItem = ((AggregatedReadingDTO) results.get(results.size() - 1)).getIdPdc();
                results = new CopyOnWriteArrayList<>();
            } else {
                results = new CopyOnWriteArrayList<>();
            }
            TypedAggregation aggregation = TypedAggregation.newAggregation(ReadingPdc.class,
                    Aggregation.match(Criteria.where("idPdc").gte(lastItem)),
                    Aggregation.project().and("idPdc").as("idPdc").and("readings")
                            .nested(Aggregation.bind("_id", "id").and("index", "index")
                                    .and("date", "date").and("idPdc", "idPdc")),
                    Aggregation.group("idPdc").push("readings").as("readings"),
                    Aggregation.sort(Sort.Direction.ASC, "_id"),
                    Aggregation.limit(getPageSize())
            ).withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());

            Collection<AggregatedReadingDTO> mappedResults = template.aggregate(aggregation, type).getMappedResults();
            results.addAll(mappedResults);
        }
    }

    @Override
    protected void doJumpToPage(int itemIndex) {

    }

    /**
     * Used to perform operations against the MongoDB instance.  Also
     * handles the mapping of documents to objects.
     *
     * @param template the MongoOperations instance to use
     * @see MongoOperations
     */

    public void setTemplate(MongoOperations template) {
        this.template = template;
    }

    public void setTargetType(Class type) {
        this.type = type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setPageSize(1000);
    }
}

