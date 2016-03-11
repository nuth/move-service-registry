package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;

import java.net.URL;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

public class EDUServiceRecord extends ServiceRecord {

    private VirkSertService virkSertService;

    public EDUServiceRecord(VirkSertService virkSertService, String orgnr) {
        super(EDU, orgnr);
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
