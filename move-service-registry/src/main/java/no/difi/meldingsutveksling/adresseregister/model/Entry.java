package no.difi.meldingsutveksling.adresseregister.model;

public class Entry {

    private String organisationNumber;
    private String x509Certificate;
    private String entryType;
    private String endPointURL;

    public Entry(String organisationNumber, String x509Certificate, String entryType, String endPointURL) {
        this.organisationNumber = organisationNumber;
        this.x509Certificate = x509Certificate;
        this.entryType = entryType;
        this.endPointURL = endPointURL;
    }

    public String getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(String organisationNumber) {
        this.organisationNumber = organisationNumber;
    }

    public String getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(String x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getEndPointURL() {
        return endPointURL;
    }

    public void setEndPointURL(String endPointURL) {
        this.endPointURL = endPointURL;
    }
}
