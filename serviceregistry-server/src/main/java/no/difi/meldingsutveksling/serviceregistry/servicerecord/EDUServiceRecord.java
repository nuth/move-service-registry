package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ServiceRegistryException;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import org.springframework.core.env.Environment;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

public class EDUServiceRecord extends ServiceRecord {

    public static final String NORWAY_PREFIX = "9908:";
    private ELMALookupService elmaLookupService;

    public EDUServiceRecord(Environment environment, VirkSertService virkSertService, ELMALookupService elmaLookupService, String orgnr) {
        super(environment, virkSertService, EDU, orgnr);
        this.elmaLookupService = elmaLookupService;
    }

    public String getEndPointURL() {

        try {
            Endpoint ep = elmaLookupService.lookup(NORWAY_PREFIX + getOrganisationNumber());
            return ep.getAddress();

        } catch (LookupException e) {
            throw new ServiceRegistryException(e);
        }
    }
}
