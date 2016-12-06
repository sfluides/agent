package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by szagoret on 12.10.2016.
 */
@Document(collection = "index")
public class ReadingPdc {

    @Id
    private String id;
    private String idPdc;
    private Double index;
    private LocalDateTime date;
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

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }
}
