package fr.osb.smartf.agent.worker.service.dto.patrimony;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 13.07.2016.
 */
@XmlRootElement(name = "PATRIMOINE_CONSTRUIT")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuiltPatrimonyDTO {

    @XmlElement(name = "RESIDENCE")
    private ResidenceDTO residence;

    @XmlElementWrapper(name = "BATIMENTS")
    @XmlElement(name = "BATIMENT")
    private List<BuildingDTO> buildings = new ArrayList<BuildingDTO>();

    @XmlElementWrapper(name = "LOGEMENTS")
    @XmlElement(name = "LOGEMENT")
    private List<HousingDTO> housings = new ArrayList<HousingDTO>();

    @XmlElementWrapper(name = "LOCAUX")
    @XmlElement(name = "LOCAL")
    private List<LocalDTO> locals = new ArrayList<LocalDTO>();

    public ResidenceDTO getResidence() {
        return residence;
    }

    public void setResidence(ResidenceDTO residence) {
        this.residence = residence;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public List<HousingDTO> getHousings() {
        return housings;
    }

    public List<LocalDTO> getLocals() {
        return locals;
    }

}
