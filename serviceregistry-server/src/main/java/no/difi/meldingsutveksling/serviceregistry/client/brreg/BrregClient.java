package no.difi.meldingsutveksling.serviceregistry.client.brreg;

import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;

import java.util.Optional;

@FunctionalInterface
public interface BrregClient {
    Optional<BrregEnhet> getBrregEnhetByOrgnr(String orgnr);
}
