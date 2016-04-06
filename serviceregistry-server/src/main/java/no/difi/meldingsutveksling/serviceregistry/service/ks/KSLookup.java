package no.difi.meldingsutveksling.serviceregistry.service.ks;


public interface KSLookup {

    /**
     * @param organizationNumber the organisation number
     * @return true if this organisation is managed by KS
     */
    boolean isKSManaged(String organizationNumber);

    /**
     * Convenience method to map any organisation numbers to KS's main number
     *
     * @param organizationNumber the organisation number
     * @return The organisation number of KS if the given organisation is managed by it.
     */
    String mapOrganizationNumber(String organizationNumber);
}