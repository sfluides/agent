package fr.osb.smartf.agent.worker.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mpaltanea on 08.06.2016.
 */
@XmlRootElement(name = "reading")
public class ReadingDTO {

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
}
