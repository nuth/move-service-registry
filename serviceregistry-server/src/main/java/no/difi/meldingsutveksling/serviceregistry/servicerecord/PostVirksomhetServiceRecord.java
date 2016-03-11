package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

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
        return null;
    }

    @Override
    public URL getEndPointURL() {
        return null;
    }
}
