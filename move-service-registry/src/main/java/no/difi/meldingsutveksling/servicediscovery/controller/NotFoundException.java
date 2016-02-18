package no.difi.meldingsutveksling.servicediscovery.controller;

import no.difi.virksert.client.VirksertClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(Exception e) {
        super(e);
    }
}
