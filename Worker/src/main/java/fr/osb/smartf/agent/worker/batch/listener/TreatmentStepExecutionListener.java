package fr.osb.smartf.agent.worker.batch.listener;

import fr.osb.smartf.agent.worker.service.util.ImportFileMsgFormatExceptionService;
import fr.osb.smartf.agent.worker.service.util.TreatmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by szagoret on 25.07.2016.
 */
public class TreatmentStepExecutionListener {

    Logger LOG = LoggerFactory.getLogger(TreatmentStepExecutionListener.class);

    public static final String ID_TREATMENT = "idTreatment";

    @Autowired
    TreatmentService treatmentService;

    @Autowired
    ImportFileMsgFormatExceptionService messageFormatService;

    @Value("#{stepExecution}")
    StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParams = stepExecution.getJobParameters();
        String importType = jobParams.getString("importType");
        String importFormat = jobParams.getString("importFormat");
        String fileName = jobParams.getString("fileName");
        String idTreatment = treatmentService.registerTreatment(importType, importFormat, fileName);
        stepExecution.getExecutionContext().put(ID_TREATMENT, idTreatment);
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        String idTreatment = stepExecution.getExecutionContext().getString(ID_TREATMENT);
        if (stepExecution.getStatus().equals(BatchStatus.FAILED)) {
            for (Throwable throwable : stepExecution.getFailureExceptions()) {
                String failureException = "";
                if (throwable.getCause()!=null && throwable.getCause().getCause()!=null) {
                    failureException = throwable.getCause().getCause().getMessage();
                }
                String failureMessage = throwable.getMessage();
                treatmentService.addTreatmentException(idTreatment, String.format("%s, cause: %s", failureException, failureMessage));
            }
            treatmentService.finalizeTreatment(idTreatment, stepExecution.getWriteCount(), ExitStatus.FAILED);
            return ExitStatus.FAILED;
        }

        if (treatmentService.isTreatmentHasErrors(idTreatment)) {
            treatmentService.finalizeTreatment(idTreatment, stepExecution.getWriteCount(), new ExitStatus("COMPLETED WITH ERRORS"));
        } else {
            treatmentService.finalizeTreatment(idTreatment, stepExecution.getWriteCount(), ExitStatus.COMPLETED);
        }
        return ExitStatus.COMPLETED;
    }

    @OnSkipInRead
    public void onSkipInRead(Exception e) {
        String formattedErrorMessage = messageFormatService.getFormattedMessage(e);
        String idTreatment = stepExecution.getExecutionContext().getString(ID_TREATMENT);
        treatmentService.addTreatmentException(idTreatment, formattedErrorMessage);
        LOG.error(formattedErrorMessage);
    }

}
