package no.difi.meldingsutveksling.serviceregistry.service.ks;


public interface KSLookup {

    /**
     * @param organisationNumber the organisation number
     * @return true if this organisation is managed by KS
     */
    boolean isKSManaged(String organisationNumber);

    /**
     * Convenience method to map organisation numbers to KS's main number
     *
     * @param organisatioNumberthe organisation number
     * @return The organisation number of KS if the given organisation is managed by it.
     */
    String mapOrganisationNumber(String organisatioNumber);
}