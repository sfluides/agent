package fr.osb.smartf.agent.worker.service.response;

import fr.osb.smartf.agent.worker.service.util.ResourceStatus;

/**
 * Created by szagoret on 12.08.2016.
 */
public class ResourcesInfo {

    private ResourceStatus mongoDbStatus;
    private ResourceStatus couchBaseStatus;
    private ResourceStatus elasticSearchStatus;

    public ResourceStatus getMongoDbStatus() {
        return mongoDbStatus;
    }

    public void setMongoDbStatus(ResourceStatus mongoDbStatus) {
        this.mongoDbStatus = mongoDbStatus;
    }

    public ResourceStatus getCouchBaseStatus() {
        return couchBaseStatus;
    }

    public void setCouchBaseStatus(ResourceStatus couchBaseStatus) {
        this.couchBaseStatus = couchBaseStatus;
    }

    public ResourceStatus getElasticSearchStatus() {
        return elasticSearchStatus;
    }

    public void setElasticSearchStatus(ResourceStatus elasticSearchStatus) {
        this.elasticSearchStatus = elasticSearchStatus;
    }
}
