package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import org.springframework.core.env.Environment;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

/**
 * Represents a Service Record for EDU messages
 *
 */
public class EDUServiceRecord extends ServiceRecord {

    EDUServiceRecord(Environment environment, VirkSertService virkSertService, String endpoint, String orgnr) {
        super(environment, virkSertService, EDU, orgnr);
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
