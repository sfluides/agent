package fr.osb.smartf.agent.worker.service.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 08.06.2016.
 */
@XmlRootElement(name = "readings")
public class ReadingDTOList {
    @XmlElement(name = "reading")
    List<ReadingDTO> readings = new ArrayList<>();

    public List<ReadingDTO> getReadings() {
        return readings;
    }
}
