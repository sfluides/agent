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
public class ResidenceDTO {

    @Id
    @XmlAttribute(name = "IDENTIFIANT")
    private String id;

    @XmlAttribute(name = "NOM")
    private String name;

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "CODE_OCEA")
    private String oceaCode;

    @XmlElement(name = "CODE_CLIENT")
    private String clientCode;

    @XmlElement(name = "ADRESSE1")
    private String address1;

    @XmlElement(name = "ADRESSE2")
    private String address2;

    @XmlElement(name = "CODEPOSTAL")
    private String postalCode;

    @XmlElement(name = "VILLE")
    private String city;

    @XmlElement(name = "PAYS")
    private String country;

    @XmlElement(name = "LONGITUDE")
    private String longitude;

    @XmlElement(name = "LATITUDE")
    private String latitude;

    @XmlElement(name = "ORGANISATION")
    private String organization;

    @XmlElement(name = "ID_ORGANISATION")
    private String organizationId;

    @XmlElement(name = "DESCRIPTION")
    private String description;

    @XmlElement(name = "ACTIF")
    private String active;

    @XmlElement(name = "IDENTIFIANT_GARDIEN")
    private String custodianId;

    @XmlElement(name = "NOM_GARDIEN")
    private String custodianLastName;

    @XmlElement(name = "PRENOM_GARDIEN")
    private String custodianFirstName;

    @XmlElement(name = "GROUPE_DE_RESIDENCE")
    private String groupOfResidence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOceaCode() {
        return oceaCode;
    }

    public void setOceaCode(String oceaCode) {
        this.oceaCode = oceaCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public String getGroupOfResidence() {
        return groupOfResidence;
    }

    public void setGroupOfResidence(String groupOfResidence) {
        this.groupOfResidence = groupOfResidence;
    }
}
