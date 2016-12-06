package fr.osb.smartf.agent.worker.batch.decider;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created by szagoret on 28.07.2016.
 */
public class ImportFileStepDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (stepExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
            if (stepExecution.getReadSkipCount() > 0) {
                return new FlowExecutionStatus("COMPLETED_WITH_ERRORS");
            } else {
                return FlowExecutionStatus.COMPLETED;
            }
        }
        return new FlowExecutionStatus(stepExecution.getExitStatus().getExitCode());
    }
}