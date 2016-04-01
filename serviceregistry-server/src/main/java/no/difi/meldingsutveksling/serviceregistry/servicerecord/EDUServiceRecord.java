package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import com.sun.corba.se.spi.activation.EndPointInfo;
import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.ServiceDiscoveryException;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.CertificateToString;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.lang.EndpointNotFoundException;
import no.difi.vefa.peppol.common.model.*;
import no.difi.vefa.peppol.lookup.LookupClient;
import no.difi.vefa.peppol.lookup.LookupClientBuilder;
import no.difi.vefa.peppol.lookup.api.LookupException;
import no.difi.vefa.peppol.security.api.PeppolSecurityException;
import no.difi.virksert.client.VirksertClientException;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Override
    public URL getEndPointURL() {

        try {
            Endpoint ep = elmaLookupService.lookup(getOrganisationNumber());
            return new URL(ep.getAddress());

        } catch (LookupException | MalformedURLException e) {
            throw new ServiceDiscoveryException(e);
        }
    }
}
