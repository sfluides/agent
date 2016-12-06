package fr.osb.smartf.agent.worker.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import fr.osb.smartf.agent.worker.elasticsearch.util.CustomDateTimeDeserializer;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 14.09.2016.
 */
@Document(indexName = "treatments", type = "treatment", shards = 1, replicas = 0)
public class Treatment {

    @Id
    private String id;
    private String workerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using= DateTimeSerializer.class)
    @JsonDeserialize(using=CustomDateTimeDeserializer.class)
    private DateTime startExecutionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using= DateTimeSerializer.class)
    @JsonDeserialize(using=CustomDateTimeDeserializer.class)
    private DateTime endExecutionDate;

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

    public DateTime getStartExecutionDate() {
        return startExecutionDate;
    }

    public void setStartExecutionDate(DateTime startExecutionDate) {
        this.startExecutionDate = startExecutionDate;
    }

    public DateTime getEndExecutionDate() {
        return endExecutionDate;
    }

    public void setEndExecutionDate(DateTime endExecutionDate) {
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
