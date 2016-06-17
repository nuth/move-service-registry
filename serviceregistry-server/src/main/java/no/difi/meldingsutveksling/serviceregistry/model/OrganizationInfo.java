package no.difi.meldingsutveksling.serviceregistry.model;

import java.io.Serializable;
import java.util.Optional;

public class OrganizationInfo implements Serializable {

    private ServiceIdentifier primaryServiceIdentifier;
    private String organisationNumber;
    private String organizationName;
    private OrganizationType organizationType;

    public OrganizationInfo() {
    }

    public OrganizationInfo(ServiceIdentifier primaryServiceIdentifier, String organisationNumber, String organizationName, OrganizationType organizationType) {
        this.primaryServiceIdentifier = primaryServiceIdentifier;
        this.organisationNumber = organisationNumber;
        this.organizationName = organizationName;
        this.organizationType = organizationType;
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

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public static class Builder {
        private OrganizationInfo organizationInfo = new OrganizationInfo();

        public Builder() {}

        public OrganizationInfo build() {
            return organizationInfo;
        }

        public Builder withPrimaryServiceIdentifier(ServiceIdentifier primaryServiceIdentifier) {
            organizationInfo.setPrimaryServiceIdentifier(primaryServiceIdentifier);
            return this;
        }

        public Builder withOrganizationNumber(String organisationNumber) {
            organizationInfo.setOrganisationNumber(organisationNumber);
            return this;
        }

        public Builder withOrganizationName(String organizationName) {
            organizationInfo.setOrganizationName(organizationName);
            return this;
        }

        public Builder setOrganizationType(OrganizationType organizationType) {
            organizationInfo.organizationType = organizationType;
            return this;
        }
    }
}