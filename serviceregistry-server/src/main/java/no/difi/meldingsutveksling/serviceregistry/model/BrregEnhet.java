package no.difi.meldingsutveksling.serviceregistry.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

import java.util.Optional;

/**
 * A POJO representing the JSON object returned from BRREG
 */
@JsonIgnoreProperties
public class BrregEnhet {
    String organisasjonsnummer;
    String navn;
    String organisasjonsform;


    public Optional<String> getOrganisasjonsnummer() {
        return Optional.of(organisasjonsnummer);
    }

    public void setOrganisasjonsnummer(String organisasjonsnummer) {
        this.organisasjonsnummer = organisasjonsnummer;
    }

    public Optional<String> getNavn() {
        return Optional.of(navn);
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Optional<String> getOrganisasjonsform() {
        return Optional.of(organisasjonsform);
    }

    public void setOrganisasjonsform(String organisasjonsform) {
        this.organisasjonsform = organisasjonsform;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("organisasjonsnummer", organisasjonsnummer)
                .add("navn", navn)
                .add("organisasjonsform", organisasjonsform)
                .toString();
    }
}
