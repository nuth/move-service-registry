package no.difi.meldingsutveksling.serviceregistry.service.brreg;

import no.difi.meldingsutveksling.serviceregistry.client.brreg.BrregClient;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import no.difi.meldingsutveksling.serviceregistry.service.brreg.dev.TestEnvironmentEnheter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;
import java.util.Optional;

@Configuration
@Profile({"dev", "test", "itest", "systest"})
public class BrregDevConfig {
    @Bean
    BrregClient brregClient() {
        Map<Optional<String>, Optional<BrregEnhet>> brregMock = new TestEnvironmentEnheter().getTestMiljøEnheter();

        return orgnr -> brregMock.getOrDefault(orgnr, Optional.empty());
    }
}
