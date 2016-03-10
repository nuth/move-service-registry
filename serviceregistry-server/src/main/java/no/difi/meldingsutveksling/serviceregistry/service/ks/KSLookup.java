package no.difi.meldingsutveksling.serviceregistry.service.ks;

public interface KSLookup {

    /**
     * @param organisationNumber
     * @return true if this organisation is managed by KS
     */
    boolean isKSManaged(String organisationNumber);

    /**
     * Convenience method to map organisation numbers to KS's main number
     *
     * @param organisatioNumber
     * @return
     */
    String mapOrganisationNumber(String organisatioNumber);

}