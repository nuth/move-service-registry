package no.difi.meldingsutveksling.serviceregistry.service.brreg.dev;

import com.google.common.collect.ImmutableMap;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;

import java.util.Arrays;
import java.util.Optional;

public class TestEnvironmentEnheter {
    private final BrregEnhet[] enheter;

    public TestEnvironmentEnheter() {
        enheter = new BrregEnhet[]{
                createBrregEnhet("Biristrand og Tjøtta", "ORGL", "910075918"),
                createBrregEnhet("Lote og Årviksand", "ORGL", "910077473"),
                createBrregEnhet("Stårheim og Røst", "ORGL", "910094092"),
                createBrregEnhet("Røn og Ranheim", "ORGL", "810076402"),
                createBrregEnhet("Dølemo og Ramberg", "ORGL", "910076787"),
                createBrregEnhet("Østby og Sandøy", "ORGL", "910094548"),
                createBrregEnhet("Reipå og Bugøynes", "ORGL", "910085379"),
                createBrregEnhet("Norfold og Henningsvær", "AS", "910071696"),
                createBrregEnhet("Aure og Darbu", "AS", "810074582")
        };
    }

    private BrregEnhet createBrregEnhet(String navn, String organisasjonsform, String organisasjonsnummer) {
        BrregEnhet biristrand = new BrregEnhet();
        biristrand.setNavn(navn);
        biristrand.setOrganisasjonsform(organisasjonsform);
        biristrand.setOrganisasjonsnummer(organisasjonsnummer);
        return biristrand;
    }

    public ImmutableMap<String, Optional<BrregEnhet>> getTestMiljøEnheter() {
        ImmutableMap.Builder<String, Optional<BrregEnhet>> builder = ImmutableMap.builder();
        Arrays.stream(enheter).forEach(e -> builder.put(e.getOrganisasjonsnummer(), Optional.of(e)));
        return builder.build();
    }
}
