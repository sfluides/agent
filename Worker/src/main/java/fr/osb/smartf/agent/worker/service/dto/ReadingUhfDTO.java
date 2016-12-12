package fr.osb.smartf.agent.worker.service.dto;

import java.util.Date;

/**
 * Created by mpaltanea on 07.12.2016.
 */
public class ReadingUhfDTO {

    private String doctype;
    private String id;
    private String guid;
    private String pdcId;
    private String date;
    private String index;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUhfAlarmeFuite() {
        return uhfAlarmeFuite;
    }

    public void setUhfAlarmeFuite(String uhfAlarmeFuite) {
        this.uhfAlarmeFuite = uhfAlarmeFuite;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
