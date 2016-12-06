package fr.osb.smartf.agent.dispatcher.service;

import fr.osb.smartf.agent.dispatcher.controller.dto.WorkerType;
import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.service.response.WorkerInfo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by szagoret on 06.06.2016.
 */
public interface AgentService {
    List<WorkerInfo> getWorkers(WorkerType workerType) throws TechnicalException;
    WorkerInfo getActiveWorker(WorkerType workerType) throws TechnicalException;
    ResponseEntity<String> delegate(WorkerInfo workerInfo, WorkerType workerType, String workerMethod, Object param, MediaType mediaType) throws TechnicalException;
    String getExternalResources();
}
