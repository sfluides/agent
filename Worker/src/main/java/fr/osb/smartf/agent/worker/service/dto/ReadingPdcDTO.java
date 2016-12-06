package fr.osb.smartf.agent.worker.service.dto;

/**
 * Created by szagoret on 12.10.2016.
 */
public class ReadingPdcDTO {

    private String idPdc;
    private String counting;
    private String date;
    private String value;

    public String getIdPdc() {
        return idPdc;
    }

    public void setIdPdc(String idPdc) {
        this.idPdc = idPdc;
    }

    public String getCounting() {
        return counting;
    }

    public void setCounting(String counting) {
        this.counting = counting;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
