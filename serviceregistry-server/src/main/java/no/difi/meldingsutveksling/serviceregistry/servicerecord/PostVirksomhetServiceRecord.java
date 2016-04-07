package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.POST_VIRKSOMHET;


public class PostVirksomhetServiceRecord extends ServiceRecord {

    public static final String CONFIG_KEY_ENDPOINT = "postvirksomhet.endPointURL";

    @Autowired
    public PostVirksomhetServiceRecord(Environment environment, VirkSertService virkSertService, String orgnr) {
        super(environment, virkSertService, POST_VIRKSOMHET, orgnr);
    }

    @Override
    public String getEndPointURL() {
        return environment.getProperty(CONFIG_KEY_ENDPOINT);
    }
}
