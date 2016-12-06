package fr.osb.smartf.agent.worker.service.util;

import fr.osb.smartf.agent.worker.elasticsearch.model.Treatment;
import fr.osb.smartf.agent.worker.elasticsearch.repository.TreatmentRepository;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by mpaltanea on 05.07.2016.
 */
@Service
public class TreatmentServiceImpl implements TreatmentService {

    Logger LOG = LoggerFactory.getLogger(WorkerStatusServiceImpl.class);

    @Autowired
    TreatmentRepository treatmentRepository;

    @Value("${worker.id}")
    private String workerId;

    @Override
    public String registerTreatment(ImportType importType, ImportFormat importFormat, Optional<String> fileName) {
        Treatment treatment = new Treatment();
        treatment.setWorkerId(workerId);
        treatment.setStartExecutionDate(new DateTime());
        treatment.setStatus(ExecutionStatus.PROGRESS.name());
        treatment.setImportType(importType.name());
        treatment.setImportFormat(importFormat.name());
        if (fileName.isPresent()) {
            treatment.setFileName(fileName.get());
        }
        treatmentRepository.save(treatment);
        return treatment.getId();
    }

    @Override
    public String registerTreatment(String importType, String importFormat, String fileName) {
        Treatment treatment = new Treatment();
        treatment.setWorkerId(workerId);
        treatment.setStartExecutionDate(new DateTime());
        treatment.setStatus(ExecutionStatus.PROGRESS.name());
        treatment.setImportType(importType);
        treatment.setImportFormat(importFormat);
        if (fileName != null) {
            treatment.setFileName(fileName);
        }
        treatmentRepository.save(treatment);
        return treatment.getId();
    }

    @Override
    public void finalizeTreatment(String treatmentId, int writeCount, ExitStatus exitStatus) {
        Treatment treatment = treatmentRepository.findOne(treatmentId);
        treatment.setWriteCount(writeCount);
        treatment.setEndExecutionDate(new DateTime());
        treatment.setDuration(Seconds.secondsBetween
                (treatment.getStartExecutionDate(), treatment.getEndExecutionDate()).getSeconds() % 60
                );
        treatment.setStatus(exitStatus.getExitCode());
        treatmentRepository.save(treatment);
    }

    @Override
    public void addTreatmentException(String treatmentId, String errorMessage) {
        Treatment treatment = treatmentRepository.findOne(treatmentId);
        treatment.getErrors().add(errorMessage);
        treatmentRepository.save(treatment);
    }

    @Override
    public boolean isTreatmentHasErrors(String treatmentId) {
        Treatment treatment = treatmentRepository.findOne(treatmentId);
        return treatment.getErrors().size() > 0;
    }
}
