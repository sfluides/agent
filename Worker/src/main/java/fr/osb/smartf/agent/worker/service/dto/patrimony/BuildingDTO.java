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
public class BuildingDTO {

    @Id
    @XmlAttribute(name = "IDENTIFIANT")
    private String id;

    @XmlAttribute(name = "IDENTIFIANT_PERE")
    private String parentId;

    @XmlAttribute(name = "NOM")
    private String name;

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "ADRESSE1")
    private String address1;

    @XmlElement(name = "ADRESSE2")
    private String address2;

    @XmlElement(name = "CODEPOSTAL")
    private String postalCode;

    @XmlElement(name = "VILLE")
    private String city;

    @XmlElement(name = "IDENTIFIANT_GARDIEN")
    private String custodianId;

    @XmlElement(name = "NOM_GARDIEN")
    private String custodianLastName;

    @XmlElement(name = "PRENOM_GARDIEN")
    private String custodianFirstName;

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustodianId() {
        return custodianId;
    }

    public void setCustodianId(String custodianId) {
        this.custodianId = custodianId;
    }

    public String getCustodianLastName() {
        return custodianLastName;
    }

    public void setCustodianLastName(String custodianLastName) {
        this.custodianLastName = custodianLastName;
    }

    public String getCustodianFirstName() {
        return custodianFirstName;
    }

    public void setCustodianFirstName(String custodianFirstName) {
        this.custodianFirstName = custodianFirstName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
