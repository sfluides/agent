package fr.osb.smartf.agent.worker.couchbase.model;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * Created by mpaltanea on 13.06.2016.
 */
@Document
public class CouchReading {

    @Id
    private String id;
    private String idPdc;
    private String date;
    private String index;
    private Double consumption;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPdc() {
        return idPdc;
    }

    public void setIdPdc(String idPdc) {
        this.idPdc = idPdc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }
}
