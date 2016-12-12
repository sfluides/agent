package fr.osb.smartf.agent.worker.couchbase.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by mpaltanea on 08.12.2016.
 */
@Document
public class ReadingUhf {

    private String doctype;
    @Id
    private String id;
    private String guid;
    private String pdcId;
    private LocalDateTime date;
    private Double index;
    private String uhfAlarmeFuite;

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPdcId() {
        return pdcId;
    }

    public void setPdcId(String pdcId) {
        this.pdcId = pdcId;
    }

    public LocalDateTime  getDate() {
        return date;
    }

    public void setDate(LocalDateTime  date) {
        this.date = date;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    public String getUhfAlarmeFuite() {
        return uhfAlarmeFuite;
    }

    public void setUhfAlarmeFuite(String uhfAlarmeFuite) {
        this.uhfAlarmeFuite = uhfAlarmeFuite;
    }
}
