package no.difi.meldingsutveksling.serviceregistry.client.brreg;

import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;

public interface BrregClient {
    BrregEnhet getBrregEnhetByOrgnr(String orgnr);
}
