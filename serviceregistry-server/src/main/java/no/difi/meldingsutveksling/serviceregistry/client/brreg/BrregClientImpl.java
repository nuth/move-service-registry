package no.difi.meldingsutveksling.serviceregistry.client.brreg;

import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

/**
 * Client for "Enhetsregisteret" at data.brreg.no
 *
 * Created by kons-mwa on 06.06.2016.
 */
@Component
public class BrregClientImpl implements BrregClient {
    private static Logger log = LoggerFactory.getLogger(BrregClientImpl.class);

    private URI uri;

    /**
     * Creates a client configured to connect to http://data.brreg.no/
     */
    public BrregClientImpl() {
        uri = URI.create("http://data.brreg.no/");
    }

    /**
     * Lookup an organization in BRREG
     * @param orgnr organization number to lookup
     * @return BRREG enhet or empty if none is found
     */
    @Override
    public Optional<BrregEnhet> getBrregEnhetByOrgnr(String orgnr) {
        URI currentURI = uri.resolve(String.format("enhetsregisteret/enhet/%s.json", orgnr));
        RestTemplate rt = new RestTemplate();
        BrregEnhet enhet= null;
        try {
            enhet= rt.getForObject(currentURI, BrregEnhet.class);
        } catch (Exception e) {
            log.error(String.format("Could not find entity for orgNr %s.", orgnr), e);
        }
        return Optional.ofNullable(enhet);
    }
}
