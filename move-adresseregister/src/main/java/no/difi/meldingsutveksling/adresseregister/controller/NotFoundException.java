package no.difi.meldingsutveksling.adresseregister.controller;

/**
 * Created by kons-gbe on 17.02.2016.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
}
