package no.difi.meldingsutveksling.serviceregistry.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties
public class BrregEnhet {
    Integer organisasjonsnummer;
    String navn;

    public Integer getOrganisasjonsnummer() {
        return organisasjonsnummer;
    }

    public void setOrganisasjonsnummer(Integer organisasjonsnummer) {
        this.organisasjonsnummer = organisasjonsnummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("organisasjonsnummer", this.organisasjonsnummer)
                .add("navn", this.navn)
                .toString();
    }
}
