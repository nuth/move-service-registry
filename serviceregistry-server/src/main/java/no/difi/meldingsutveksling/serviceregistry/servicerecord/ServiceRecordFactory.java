package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import org.springframework.core.env.Environment;

/**
 * Factory method class to create Service Records based on lookup endpoint urls and certificates corresponding to those
 * services
 */
public class ServiceRecordFactory {
    private Environment environment;
    private VirkSertService virksertService;
    private ELMALookupService elmaLookupService;
    private KSLookup ksLookup;
    private static final String NORWAY_PREFIX = "9908:";

    public ServiceRecordFactory(Environment environment, VirkSertService virksertService, ELMALookupService elmaLookupService, KSLookup ksLookup) {
        this.environment = environment;
        this.virksertService = virksertService;
        this.elmaLookupService = elmaLookupService;
        this.ksLookup = ksLookup;
    }

    public ServiceRecord createEduServiceRecord(String orgnr) {
        String finalOrgNumber = ksLookup.mapOrganizationNumber(orgnr);
        Endpoint ep = elmaLookupService.lookup(NORWAY_PREFIX + finalOrgNumber);
        return new EDUServiceRecord(environment, virksertService, ep.getAddress(), orgnr);
    }

    public ServiceRecord createPostVirksomhetServiceRecord(String orgnr) {
        return new PostVirksomhetServiceRecord(environment, virksertService, orgnr);
    }
}
