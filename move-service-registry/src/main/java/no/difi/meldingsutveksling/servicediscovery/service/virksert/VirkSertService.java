package no.difi.meldingsutveksling.servicediscovery.service.virksert;


import no.difi.meldingsutveksling.servicediscovery.controller.NotFoundException;
import no.difi.virksert.client.VirksertClient;
import no.difi.virksert.client.VirksertClientBuilder;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.cert.Certificate;

@Component
public class VirkSertService {

    private VirksertClient virksertClient;

    @Autowired
    private Environment environment;

    public VirkSertService() {
    }

    public VirkSertService(VirksertClient virksertClient) {
        this.virksertClient = virksertClient;
    }

    @PostConstruct
    public void init() {
        virksertClient = VirksertClientBuilder.newInstance()
                .setScope("no.difi.meldingsutveksling.servicediscovery.service.virksert.DemoScope")
                .setUri(environment.getProperty("adresseregister.endPointURL")).build();
    }

    public Certificate getCertificate(String orgNumber) {
        try {
            return virksertClient.fetch(orgNumber);
        } catch (VirksertClientException e) {
            throw new NotFoundException(e);
        }
    }
}
