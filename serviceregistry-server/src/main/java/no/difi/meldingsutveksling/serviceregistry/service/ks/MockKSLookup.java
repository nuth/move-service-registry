package no.difi.meldingsutveksling.serviceregistry.service.ks;

import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Change this when a lookup service is available for KS
 */
@Profile({"dev,systest,itest,staging"})
public class MockKSLookup implements KSLookup {

    private static final String KS_ORGNR = "910076787";
    private static List<String> orgNumberstoreplaceWithKS = new ArrayList<>();

    static {
        orgNumberstoreplaceWithKS.add("910076787");
        orgNumberstoreplaceWithKS.add("910951688");
    }

    @Override
    public boolean isKSManaged(String organisationNumber) {
        return organisationNumber.contains(organisationNumber);
    }

    @Override
    public String mapOrganisationNumber(String organisatioNumber) {
        if (isKSManaged(organisatioNumber)) {
            return KS_ORGNR;
        } else
            return organisatioNumber;
    }
}
