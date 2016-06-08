package no.difi.meldingsutveksling.serviceregistry.model;

import java.io.Serializable;

public class OrganizationInfo implements Serializable {

    private ServiceIdentifier primaryServiceIdentifier;
    private String organisationNumber;
    private String organizationName;

    public OrganizationInfo() {
    }

    public OrganizationInfo(String organisationNumber, String organizationName, ServiceIdentifier primaryServiceIdentifier) {
        this.organisationNumber = organisationNumber;
        this.organizationName = organizationName;
        this.primaryServiceIdentifier = primaryServiceIdentifier;
    }

    public ServiceIdentifier getPrimaryServiceIdentifier() {
        return primaryServiceIdentifier;
    }

    public void setPrimaryServiceIdentifier(ServiceIdentifier primaryServiceIdentifier) {
        this.primaryServiceIdentifier = primaryServiceIdentifier;
    }

    public String getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(String organisationNumber) {
        this.organisationNumber = organisationNumber;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}


