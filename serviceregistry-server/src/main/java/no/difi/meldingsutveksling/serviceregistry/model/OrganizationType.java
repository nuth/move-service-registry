package no.difi.meldingsutveksling.serviceregistry.model;

import com.google.common.base.Objects;

/**
 * Represents an organization according to BRREG
 *
 * See http://hotell.difi.no/?dataset=brreg/organisasjonsform
 *
 */
public class OrganizationType {
    private String name;
    private String akronym;

    public OrganizationType(String name, String akronym) {
        this.name = name;
        this.akronym = akronym;
    }

    public String getName() {
        return name;
    }

    public String getAkronym() {
        return akronym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationType that = (OrganizationType) o;
        return Objects.equal(akronym, that.akronym);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(akronym);
    }
}
