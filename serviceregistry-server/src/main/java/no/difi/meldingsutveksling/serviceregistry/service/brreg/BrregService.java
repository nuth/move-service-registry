package no.difi.meldingsutveksling.serviceregistry.service.brreg;

import no.difi.meldingsutveksling.serviceregistry.client.brreg.BrregClient;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrregService {

    private static final Logger log = LoggerFactory.getLogger(BrregService.class);

    private BrregClient brregClient;

    @Autowired
    public BrregService(BrregClient brregClient) {
        this.brregClient= brregClient;
    }

    public String getOrgName(String orgNr) {
        BrregEnhet enhet = brregClient.getBrregEnhetByOrgnr(orgNr);
        if (enhet != null) {
            return enhet.getNavn();
        }
        log.error(String.format("Could not find entity for orgNr %s.", orgNr));
        return "";
    }
}
