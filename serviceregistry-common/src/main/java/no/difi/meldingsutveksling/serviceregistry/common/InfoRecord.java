package no.difi.meldingsutveksling.serviceregistry.common;

import java.io.Serializable;

/**
 *
 */
public class InfoRecord implements Serializable {

    private String primaryServiceIdentifier;
    private String organisationNumber;

    public InfoRecord() {
    }

    public InfoRecord(String primaryServiceIdentifier, String organisationNumber) {
        this.primaryServiceIdentifier = primaryServiceIdentifier;
        this.organisationNumber = organisationNumber;
    }

    public String getPrimaryServiceIdentifier() {
        return primaryServiceIdentifier;
    }

    public void setPrimaryServiceIdentifier(String primaryServiceIdentifier) {
        this.primaryServiceIdentifier = primaryServiceIdentifier;
    }

    public String getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(String organisationNumber) {
        this.organisationNumber = organisationNumber;
    }
}


