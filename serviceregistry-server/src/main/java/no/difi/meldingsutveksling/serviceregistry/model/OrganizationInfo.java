package no.difi.meldingsutveksling.serviceregistry.model;

import java.io.Serializable;

public class OrganizationInfo implements Serializable {

    private ServiceIdentifier primaryServiceIdentifier;
    private String organisationNumber;

    public OrganizationInfo() {
    }

    public OrganizationInfo(String organisationNumber, ServiceIdentifier primaryServiceIdentifier) {
        this.organisationNumber = organisationNumber;
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
}


