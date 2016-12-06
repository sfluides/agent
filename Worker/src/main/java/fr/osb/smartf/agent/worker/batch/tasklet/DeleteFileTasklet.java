package fr.osb.smartf.agent.worker.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * Created by szagoret on 25.07.2016.
 */
public class DeleteFileTasklet implements Tasklet {

    Logger LOG = LoggerFactory.getLogger(DeleteFileTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String filePath = (String) chunkContext.getStepContext().getJobParameters().get("pathToFile");
        try {
            Files.delete(Paths.get(filePath));
        } catch (NoSuchFileException x) {
            LOG.error("%s: no such" + " file %n", filePath);
        } catch (NullPointerException e) {
            LOG.error("File path is null %s", e.getMessage());
        } catch (IOException x) {
            // File permission problems are caught here.
            LOG.error(x.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
}
