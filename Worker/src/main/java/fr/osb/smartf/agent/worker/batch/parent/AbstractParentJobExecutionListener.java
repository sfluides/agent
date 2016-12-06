package fr.osb.smartf.agent.worker.batch.parent;

import fr.osb.smartf.agent.worker.service.util.WorkerStatus;
import fr.osb.smartf.agent.worker.service.util.WorkerStatusService;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by szagoret on 21.07.2016.
 */
public class AbstractParentJobExecutionListener implements JobExecutionListener {

    @Autowired
    WorkerStatusService workerStatusService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        workerStatusService.updateWorkerStatus(WorkerStatus.busy);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        workerStatusService.updateWorkerStatus(WorkerStatus.available);
    }
}
