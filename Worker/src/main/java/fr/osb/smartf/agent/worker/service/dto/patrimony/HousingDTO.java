package fr.osb.smartf.agent.worker.service.dto.patrimony;

import com.couchbase.client.java.repository.annotation.Id;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mpaltanea on 13.07.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HousingDTO {

    @Id
    @XmlAttribute(name = "IDENTIFIANT")
    private String id;

    @XmlAttribute(name = "IDENTIFIANT_PERE")
    private String parentId;

    @XmlAttribute(name = "NOM")
    private String name;

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "ETAGE")
    private String level;

    @XmlElement(name = "SURFACE_HABITABLE")
    private String livingSpace;

    @XmlElement(name = "TYPE_DE_LOGEMENT")
    private String type;

    @XmlElement(name = "PORTE")
    private String door;

    @XmlElement(name = "NUMERO_ORDRE")
    private String orderNumber;

    @XmlElement(name = "REFERENCE_LOT")
    private String lotReference;

    @XmlElement(name = "TANTIEMES")
    private String royalties;

    @XmlElement(name = "VACANT")
    private String vacant;

    @XmlElement(name = "DESCRIPTION")
    private String description;

    @XmlElement(name = "OCCUPANTS")
    private String occupants;

    @XmlElement(name = "IDENTIFIANT_LOCATAIRE")
    private String tenantId;

    @XmlElement(name = "NOM_LOCATAIRE")
    private String tenantLastName;

    @XmlElement(name = "PRENOM_LOCATAIRE")
    private String tenantFirstName;

    @XmlElement(name = "DATE_ENTREE")
    private String entryDate;

    @XmlElement(name = "IDENTIFIANT_PROPRIETAIRE")
    private String ownerId;

    @XmlElement(name = "NOM_PROPRIETAIRE")
    private String ownerLastName;

    @XmlElement(name = "PRENOM_PROPRIETAIRE")
    private String ownerFirstName;

    @XmlElement(name = "DATE_ACHAT")
    private String purchaseDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(String livingSpace) {
        this.livingSpace = livingSpace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLotReference() {
        return lotReference;
    }

    public void setLotReference(String lotReference) {
        this.lotReference = lotReference;
    }

    public String getRoyalties() {
        return royalties;
    }

    public void setRoyalties(String royalties) {
        this.royalties = royalties;
    }

    public String getVacant() {
        return vacant;
    }

    public void setVacant(String vacant) {
        this.vacant = vacant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOccupants() {
        return occupants;
    }

    public void setOccupants(String occupants) {
        this.occupants = occupants;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantLastName() {
        return tenantLastName;
    }

    public void setTenantLastName(String tenantLastName) {
        this.tenantLastName = tenantLastName;
    }

    public String getTenantFirstName() {
        return tenantFirstName;
    }

    public void setTenantFirstName(String tenantFirstName) {
        this.tenantFirstName = tenantFirstName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
