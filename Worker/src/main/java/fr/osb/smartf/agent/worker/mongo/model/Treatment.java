package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 05.07.2016.
 */
@Document(collection = "treatments")
public class Treatment {
    @Id
    private String id;
    private String workerId;
    private LocalDateTime startExecutionDate;
    private LocalDateTime endExecutionDate;
    private long duration;
    private String status;
    private String importType;
    private String importFormat;
    private String fileName;
    private int writeCount;
    private List<String> errors = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public LocalDateTime getStartExecutionDate() {
        return startExecutionDate;
    }

    public void setStartExecutionDate(LocalDateTime startExecutionDate) {
        this.startExecutionDate = startExecutionDate;
    }

    public LocalDateTime getEndExecutionDate() {
        return endExecutionDate;
    }

    public void setEndExecutionDate(LocalDateTime endExecutionDate) {
        this.endExecutionDate = endExecutionDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(int writeCount) {
        this.writeCount = writeCount;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
