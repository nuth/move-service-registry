package no.difi.meldingsutveksling.serviceregistry.service.persistence;

import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory storage of what organisation that should use what service for
 * transport (post virksomhet / edu etc).
 */
@Profile({"dev", "itest", "systest", "staging"})
@Component
public class PrimaryServiceStore {

    private static final Map<String, ServiceIdentifier> primaryStore = new HashMap<>();

    public ServiceIdentifier getPrimaryOverride(String orgNumber) {
        return primaryStore.get(orgNumber);
    }

    public void setPrimaryOverride(String orgNumber, ServiceIdentifier serviceIdentifier) {
        primaryStore.put(orgNumber, serviceIdentifier);
    }
}
