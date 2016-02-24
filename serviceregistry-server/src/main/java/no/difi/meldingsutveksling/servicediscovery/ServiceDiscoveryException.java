package no.difi.meldingsutveksling.servicediscovery;

import no.difi.vefa.peppol.common.api.PeppolException;
import no.difi.virksert.client.VirksertClientException;

public class ServiceDiscoveryException extends RuntimeException {
    public ServiceDiscoveryException(Exception e) {
        super(e);
    }

}