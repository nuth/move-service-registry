package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import org.springframework.core.env.Environment;

public class ServiceRecordFactory {
    Environment environment;
    VirkSertService virksertService;
    ELMALookupService elmaLookupService;
    KSLookup ksLookup;

    public ServiceRecordFactory(Environment environment, VirkSertService virksertService, ELMALookupService elmaLookupService, KSLookup ksLookup) {
        this.environment = environment;
        this.virksertService = virksertService;
        this.elmaLookupService = elmaLookupService;
        this.ksLookup = ksLookup;
    }

    public ServiceRecord createEduServiceRecord(String orgnr) {
        return new EDUServiceRecord(environment, virksertService, elmaLookupService, ksLookup, orgnr);
    }

    public ServiceRecord createPostVirksomhetServiceRecord(String orgnr) {
        return new PostVirksomhetServiceRecord(environment, virksertService, orgnr);
    }
}
