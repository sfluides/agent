package fr.osb.smartf.agent.worker.service.util;

import fr.osb.smartf.agent.worker.service.response.ResourcesInfo;

/**
 * Created by szagoret on 12.08.2016.
 */
public interface ResourcesStatusService {
    ResourceStatus getMongoDbStatus();

    ResourceStatus getCouchBaseStatus();

    ResourceStatus getElasticSearchStatus();

    ResourcesInfo getWorkerResourcesInfo();
}
