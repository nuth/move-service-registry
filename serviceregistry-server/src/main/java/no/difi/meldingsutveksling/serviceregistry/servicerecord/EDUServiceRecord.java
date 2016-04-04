package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.ServiceRegistryException;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.CertificateToString;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import no.difi.virksert.client.VirksertClientException;

import java.security.cert.Certificate;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

public class EDUServiceRecord extends ServiceRecord {

    public static final String NORWAY_PREFIX = "9908:";
    private VirkSertService virkSertService;
    private ELMALookupService elmaLookupService;


    public EDUServiceRecord(VirkSertService virkSertService, ELMALookupService elmaLookupService, String orgnr) {
        super(EDU, orgnr);
        this.virkSertService = virkSertService;
        this.elmaLookupService = elmaLookupService;
    }

    @Override
    public String getX509Certificate() {
        try {
            Certificate c = virkSertService.getCertificate(getOrganisationNumber());
            return CertificateToString.toString(c);
        } catch (VirksertClientException e) {
            throw new ResourceNotFoundException(e);
        }
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
