package fr.osb.smartf.agent.worker.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by mpaltanea on 01.08.2016.
 */
public class UploadFileRepositoryTasklet implements Tasklet {

    Logger LOG = LoggerFactory.getLogger(UploadFileRepositoryTasklet.class);

    /*@Autowired
    ContentRepositoryService contentRepositoryService;*/

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // uploading file to Sling
        /*String filePath = (String) chunkContext.getStepContext().getJobParameters().get("pathToFile");
        Resource resource = new FileSystemResource(filePath);
        contentRepositoryService.uploadResource(resource, "importFiles", "import.xml");
        contentRepositoryService.deleteResource("importFiles/import.xml");*/
        return RepeatStatus.FINISHED;
    }
}
