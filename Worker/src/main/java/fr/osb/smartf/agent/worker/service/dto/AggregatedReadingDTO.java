package fr.osb.smartf.agent.worker.service.dto;

import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szagoret on 23.06.2016.
 */
public class AggregatedReadingDTO {

    @Id
    private String idPdc;

    private List<ReadingPdc> readings = new ArrayList<>();

    public void setIdPdc(String idPdc) {
        this.idPdc = idPdc;
    }

    public String getIdPdc() {
        return idPdc;
    }

    public List<ReadingPdc> getReadings() {
        return readings;
    }

    public void setReadings(List<ReadingPdc> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "AggregatedReadingDTO{" +
                "idPdc=" + idPdc +
                ", readings=" + readings +
                '}';
    }
}
