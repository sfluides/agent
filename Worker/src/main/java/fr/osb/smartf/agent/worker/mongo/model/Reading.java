package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mpaltanea on 08.06.2016.
 */
@Document(collection = "readings")
public class Reading {

    @Id
    private Long id;
    private String date;
    private Long idPdc;
    private Integer index;
    private String qmin;
    private String cons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getIdPdc() {
        return idPdc;
    }

    public void setIdPdc(Long idPdc) {
        this.idPdc = idPdc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getQmin() {
        return qmin;
    }

    public void setQmin(String qmin) {
        this.qmin = qmin;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", idPdc=" + idPdc +
                ", index=" + index +
                ", qmin='" + qmin + '\'' +
                ", cons='" + cons + '\'' +
                '}';
    }
}
