package fr.osb.smartf.agent.worker.service.util;

import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import fr.osb.smartf.agent.worker.service.response.ResourcesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by szagoret on 12.08.2016.
 */
@Service
public class ResourcesStatusServiceImpl implements ResourcesStatusService {
    Logger LOG = LoggerFactory.getLogger(ResourcesStatusServiceImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${elasticsearch.host}")
    String elasticSearchHost;

    @Value("${couch.server.hosts}")
    String couchBaseHost;

    @Value("${couch.server.bucket}")
    String couchBaseBucket;

    @Override
    public ResourceStatus getMongoDbStatus() {

        try {
            CommandResult commandResult = mongoTemplate.executeCommand(BasicDBObjectBuilder.start(ImmutableMap.builder().put("ping", 1).build()).get());
            LOG.info("execute: {}", commandResult);
            if (commandResult.ok()) {
                return ResourceStatus.up;
            } else {
                return ResourceStatus.down;
            }
        } catch (DataAccessResourceFailureException e) {
            return ResourceStatus.down;
        }
    }

    @Override
    public ResourceStatus getCouchBaseStatus() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://" + couchBaseHost + ":8091/pools/default/buckets/" + couchBaseBucket, String.class);
            return ResourceStatus.up;
        } catch (Exception e) {
            return ResourceStatus.down;
        }
    }

    @Override
    public ResourceStatus getElasticSearchStatus() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(elasticSearchHost, String.class);
            return ResourceStatus.up;
        } catch (Exception e) {
            return ResourceStatus.down;
        }
    }

    @Override
    public ResourcesInfo getWorkerResourcesInfo() {

        ResourcesInfo resourcesInfo = new ResourcesInfo();

        ResourceStatus mongoDbStatus = this.getMongoDbStatus();
        resourcesInfo.setMongoDbStatus(mongoDbStatus);

        ResourceStatus couchResourceInfo = this.getCouchBaseStatus();
        resourcesInfo.setCouchBaseStatus(couchResourceInfo);

        ResourceStatus elasticResourceInfo = this.getElasticSearchStatus();
        resourcesInfo.setElasticSearchStatus(elasticResourceInfo);

        return resourcesInfo;
    }
}
