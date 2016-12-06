package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.util.ImportFormat;
import fr.osb.smartf.agent.worker.service.util.ImportType;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by szagoret on 17.06.2016.
 */
@Service
public class LaunchJobServiceImpl implements LaunchJobService {

    @Value("${import.basedir}")
    private String importDir;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRegistry jobRegistry;

    @Override
    public JobExecution launchJob(String jobName) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();
        return jobLauncher.run(jobRegistry.getJob(jobName), jobParameters);

    }

    @Override
    public JobExecution launchJob(String jobName, ImportType importType, ImportFormat importFormat, ImportFileParams importFileParams) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("importType", importType.name())
                .addString("importFormat", importFormat.name())
                .addString("fileName", importFileParams.getFileName())
                .addString("pathToFile", importDir + "/" + importFileParams.getImportType() +
                        "/" + importFileParams.getFileName()).toJobParameters();
        return jobLauncher.run(jobRegistry.getJob(jobName), jobParameters);

    }
}
