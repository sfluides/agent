package fr.osb.smartf.agent.worker.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by szagoret on 28.07.2016.
 */
public class MoveFileTasklet implements Tasklet {
    Logger LOG = LoggerFactory.getLogger(MoveFileTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOG.info("Please provide implementation of moving file");
        return RepeatStatus.FINISHED;
    }
}
