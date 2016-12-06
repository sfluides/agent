package fr.osb.smartf.agent.worker.couchbase.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

/**
 * Created by szagoret on 12.10.2016.
 */
@Document
public class AggregatedCouchReadings {
    @Id
    private String id;
    private String doctype;
    private String guid;
    private String pdc;
    private String pdcId;
    private List<KeyValue> consumption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public String getPdcId() {
        return pdcId;
    }

    public void setPdcId(String pdcId) {
        this.pdcId = pdcId;
    }

    public List<KeyValue> getConsumption() {
        return consumption;
    }

    public void setConsumption(List<KeyValue> consumption) {
        this.consumption = consumption;
    }
}
