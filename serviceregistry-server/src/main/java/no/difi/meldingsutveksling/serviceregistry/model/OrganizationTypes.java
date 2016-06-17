package no.difi.meldingsutveksling.serviceregistry.model;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Constants in accordance with http://hotell.difi.no/?dataset=brreg/organisasjonsform
 */
public class OrganizationTypes {
    public static final OrganizationType AS = new OrganizationType("Aksjeselskap", "AS");
    public static final OrganizationType ORGL = new OrganizationType("Organisasjonsledd", "ORGL");

    public Set<OrganizationType> privateOrganization() {
        return Sets.newHashSet(AS);
    }

    public Set<OrganizationType> publicOrganization() {
        return Sets.newHashSet(ORGL);
    }
}
