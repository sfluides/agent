package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.util.ImportFormat;
import fr.osb.smartf.agent.worker.service.util.ImportType;
import org.springframework.batch.core.JobExecution;

/**
 * Created by szagoret on 17.06.2016.
 */
public interface LaunchJobService {
    JobExecution launchJob(String jobName) throws Exception;
    JobExecution launchJob(String jobName, ImportType importType, ImportFormat importFormat, ImportFileParams importFileParams) throws Exception;
}
