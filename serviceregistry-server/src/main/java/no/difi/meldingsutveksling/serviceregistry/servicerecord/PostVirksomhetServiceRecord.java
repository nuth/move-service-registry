package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.CertificateToString;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.POST_VIRKSOMHET;


public class PostVirksomhetServiceRecord extends ServiceRecord {

    private VirkSertService virkSertService;

    @Autowired
    public PostVirksomhetServiceRecord(VirkSertService virkSertService, String orgnr) {
        super(POST_VIRKSOMHET, orgnr);
        this.virkSertService = virkSertService;
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
            return new URL("http://www.vg.no");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
