package fr.osb.smartf.agent.worker.service.dto.patrimony;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 13.07.2016.
 */
@XmlRootElement(name = "PATRIMOINE_INSTALLE")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstalledPatrimonyDTO {

    @XmlElement(name = "RESIDENCE")
    private ResidenceDTO residence;

    @XmlElementWrapper(name = "POINTSDECOMPTAGE")
    @XmlElement(name = "POINTDECOMPTAGE")
    private List<CountingPointDTO> countingPoints = new ArrayList<CountingPointDTO>();

    @XmlElementWrapper(name = "COMPTEURS")
    @XmlElement(name = "COMPTEUR")
    private List<CounterDTO> counters = new ArrayList<CounterDTO>();

    public ResidenceDTO getResidence() {
        return residence;
    }

    public void setResidence(ResidenceDTO residence) {
        this.residence = residence;
    }

    public List<CountingPointDTO> getCountingPoints() {
        return countingPoints;
    }

    public List<CounterDTO> getCounters() {
        return counters;
    }
}
