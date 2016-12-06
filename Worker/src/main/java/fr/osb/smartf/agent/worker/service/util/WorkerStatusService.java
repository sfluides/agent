package fr.osb.smartf.agent.worker.service.util;

/**
 * Created by szagoret on 15.06.2016.
 */
public interface WorkerStatusService {
    void updateWorkerStatus(WorkerStatus workerStatus);
    WorkerStatus getWorkerStatus();
}
