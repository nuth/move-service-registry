package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.CertificateToString;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.security.cert.Certificate;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.POST_VIRKSOMHET;


public class PostVirksomhetServiceRecord extends ServiceRecord {

    public static final String CONFIG_KEY = "postvirksomhet.endPointURL";

    @Autowired
    public PostVirksomhetServiceRecord(Environment environment, VirkSertService virkSertService, String orgnr) {
        super(environment, virkSertService, POST_VIRKSOMHET, orgnr);
    }

    @Override
    public String getEndPointURL() {
        return environment.getProperty(CONFIG_KEY);
    }
}
