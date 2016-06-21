package no.difi.meldingsutveksling.serviceregistry.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Constants in accordance with http://hotell.difi.no/?dataset=brreg/organisasjonsform
 */
public class OrganizationTypes {
    private static final OrganizationType AS = new OrganizationType("Aksjeselskap", "AS");
    public static final OrganizationType ORGL = new OrganizationType("Organisasjonsledd", "ORGL");
    static final Map<String, OrganizationType> all =
            ImmutableMap.<String, OrganizationType>builder().put(AS.getAkronym(), AS)
                    .put(ORGL.getAkronym(), ORGL).build();

    /**
     * @return collection of all known private organization types
     */
    public Set<OrganizationType> privateOrganization() {
        return Sets.newHashSet(AS);
    }

    /**
     * @return collection of all known public organization types
     */
    public Set<OrganizationType> publicOrganization() {
        return Sets.newHashSet(ORGL);
    }
}
