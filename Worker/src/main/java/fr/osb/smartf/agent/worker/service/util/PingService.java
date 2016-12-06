package fr.osb.smartf.agent.worker.service.util;

import fr.osb.smartf.agent.worker.service.response.WorkerInfo;

/**
 * Created by szagoret on 15.06.2016.
 */
public interface PingService {
    WorkerInfo getWorkerStatus();
}
