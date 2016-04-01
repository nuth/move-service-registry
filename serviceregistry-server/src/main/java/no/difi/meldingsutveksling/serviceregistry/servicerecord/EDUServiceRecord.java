package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;

import java.net.URL;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

public class EDUServiceRecord extends ServiceRecord {

    private VirkSertService virkSertService;

    public EDUServiceRecord(VirkSertService virkSertService, String orgnr) {
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
'

    public URL getEndPointURL() {

        try {
            Endpoint ep = elmaLookupService.lookup(getOrganisationNumber());
            return new URL(ep.getAddress());

        } catch (LookupException | MalformedURLException e) {
            throw new ServiceDiscoveryException(e);
        }
    }
}
