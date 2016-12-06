package fr.osb.smartf.agent.dispatcher.controller.dto;

import java.time.LocalDateTime;

/**
 * Created by mpaltanea on 06.07.2016.
 */
public class TreatmentParam {

    private String workerId;
    private String status;
    private String importType;
    private String importFormat;
    private LocalDateTime startDate;

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getImportFormat() {
        return importFormat;
    }

    public void setImportFormat(String importFormat) {
        this.importFormat = importFormat;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
