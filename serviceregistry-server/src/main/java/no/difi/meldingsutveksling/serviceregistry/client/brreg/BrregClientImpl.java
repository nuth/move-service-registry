package no.difi.meldingsutveksling.serviceregistry.client.brreg;

import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Client for "Enhetsregisteret" at data.brreg.no
 *
 * Created by kons-mwa on 06.06.2016.
 */
@Component
public class BrregClientImpl implements BrregClient {
    private static Logger log = LoggerFactory.getLogger(BrregClientImpl.class);

    private URI uri;

    public BrregClientImpl() {
        uri = URI.create("http://data.brreg.no/");
    }

    public BrregEnhet getBrregEnhetByOrgnr(String orgnr) {
        URI currentURI = uri.resolve(String.format("enhetsregisteret/enhet/%s.json", orgnr));
        RestTemplate rt = new RestTemplate();
        BrregEnhet enhet= null;
        try {
            enhet= rt.getForObject(currentURI, BrregEnhet.class);
        } catch (Exception e) {
            log.error("Failed to retrieve entity. "+e.getMessage());
        }
        return enhet;
    }
}
