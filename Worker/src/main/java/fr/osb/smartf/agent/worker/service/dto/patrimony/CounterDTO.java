package fr.osb.smartf.agent.worker.service.dto.patrimony;

import com.couchbase.client.java.repository.annotation.Id;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mpaltanea on 19.07.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CounterDTO {

    @Id
    @XmlAttribute(name = "IDENTIFIANT")
    private String id;

    @XmlAttribute(name = "IDENTIFIANT_PERE")
    private String parentId;

    @XmlElement(name = "INDEX_DEPOSE")
    private String removalIndex;

    @XmlElement(name = "INDEX_POSE")
    private String installationIndex;

    @XmlElement(name = "DATE_POSE")
    private String dateInstallation;

    @XmlElement(name = "MODELE")
    private String model;

    @XmlElement(name = "NUMERO_DE_SERIE")
    private String series;

    @XmlElement(name = "MARQUE")
    private String brand;

    @XmlElement(name = "ANNEE_DE_FABRICATION")
    private String yearOfManufacture;

    @XmlElement(name = "TYPE_DEBIT")
    private String debitType;

    @XmlElement(name = "ACTIF")
    private String active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemovalIndex() {
        return removalIndex;
    }

    public void setRemovalIndex(String removalIndex) {
        this.removalIndex = removalIndex;
    }

    public String getInstallationIndex() {
        return installationIndex;
    }

    public void setInstallationIndex(String installationIndex) {
        this.installationIndex = installationIndex;
    }

    public String getDateInstallation() {
        return dateInstallation;
    }

    public void setDateInstallation(String dateInstallation) {
        this.dateInstallation = dateInstallation;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getDebitType() {
        return debitType;
    }

    public void setDebitType(String debitType) {
        this.debitType = debitType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
