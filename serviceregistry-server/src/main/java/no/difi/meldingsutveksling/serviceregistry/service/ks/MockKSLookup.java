package no.difi.meldingsutveksling.serviceregistry.service.ks;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove this when a lookup service is available for KS,
 * naive implementation to be replaced by a persistent solution
 */
@Component
@Profile({"dev", "systest", "itest", "staging"})
public class MockKSLookup implements KSLookup {

    public static final String KS_ORGNR = "910076787";
    public static final String KS_MANAGED_1 = "910951688";
    public static final String KS_MANAGED_2 = "910076787";

    private static List<String> orgNumberstoreplaceWithKS = new ArrayList<>();

    static {
        orgNumberstoreplaceWithKS.add(KS_MANAGED_1);
        orgNumberstoreplaceWithKS.add(KS_MANAGED_2);
    }

    @Override
    public boolean isKSManaged(String organizationNumber) {
        return orgNumberstoreplaceWithKS.contains(organizationNumber);
    }

    @Override
    public String mapOrganizationNumber(String organizationNumber) {
        if (isKSManaged(organizationNumber)) {
            return KS_ORGNR;
        } else
            return organizationNumber;
    }
}
