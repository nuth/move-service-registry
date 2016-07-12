package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import org.springframework.core.env.Environment;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

/**
 * Represents a Service Record for EDU messages
 *
 */
public class EDUServiceRecord extends ServiceRecord {

    EDUServiceRecord(Environment environment, String pemCertificate, String endpoint, String orgnr) {
        super(environment, pemCertificate, EDU, orgnr);
        this.endpointUrl = endpoint;
    }

    @Override
    public String getEndPointURL() {
        return endpointUrl;
    }

    @Override
    public String getOrganisationNumber() {
        return organisationNumber;
    }
}
