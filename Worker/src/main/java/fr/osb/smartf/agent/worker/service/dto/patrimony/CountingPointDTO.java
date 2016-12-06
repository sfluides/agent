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
public class CountingPointDTO {

    @Id
    @XmlAttribute(name = "IDENTIFIANT")
    private String id;

    @XmlAttribute(name = "IDENTIFIANT_PERE")
    private String parentId;

    @XmlAttribute(name = "NOM")
    private String name;

    @XmlElement(name = "TYPE")
    private String type;

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "TYPE_DE_PDC")
    private String typePDC;

    @XmlElement(name = "TELERELEVE")
    private String remoteReading;

    @XmlElement(name = "TYPE_RELEVE")
    private String readingType;

    @XmlElement(name = "FREQUENCE_RELEVE")
    private String readingFrequency;

    @XmlElement(name = "ALIMENTATION")
    private String alimentation;

    @XmlElement(name = "LOCALISATION_PHYSIQUE")
    private String physical;

    @XmlElement(name = "PIECE")
    private String piece;

    @XmlElement(name = "DESCRIPTION")
    private String description;

    @XmlElement(name = "PROPRIETAIRE")
    private String owner;

    @XmlElement(name = "ACTIF")
    private String active;

    @XmlElement(name = "REF_COMPLEMENTAIRE")
    private String supplementaryReference;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypePDC() {
        return typePDC;
    }

    public void setTypePDC(String typePDC) {
        this.typePDC = typePDC;
    }

    public String getRemoteReading() {
        return remoteReading;
    }

    public void setRemoteReading(String remoteReading) {
        this.remoteReading = remoteReading;
    }

    public String getReadingType() {
        return readingType;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public String getReadingFrequency() {
        return readingFrequency;
    }

    public void setReadingFrequency(String readingFrequency) {
        this.readingFrequency = readingFrequency;
    }

    public String getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(String alimentation) {
        this.alimentation = alimentation;
    }

    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSupplementaryReference() {
        return supplementaryReference;
    }

    public void setSupplementaryReference(String supplementaryReference) {
        this.supplementaryReference = supplementaryReference;
    }
}

