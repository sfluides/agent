package fr.osb.smartf.agent.worker.service.util;

import org.springframework.batch.core.ExitStatus;

import java.util.Optional;

/**
 * Created by mpaltanea on 05.07.2016.
 */
public interface TreatmentService {
    String registerTreatment(ImportType importType, ImportFormat importFormat, Optional<String> fileName);

    String registerTreatment(String importType, String importFormat, String fileName);

    void finalizeTreatment(String treatmentId, int writeCount, ExitStatus exitStatus);

    void addTreatmentException(String treatmentId, String errorMessage);

    boolean isTreatmentHasErrors(String treatmentId);
}
