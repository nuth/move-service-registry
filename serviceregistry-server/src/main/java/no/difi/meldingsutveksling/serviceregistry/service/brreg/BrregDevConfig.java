package no.difi.meldingsutveksling.serviceregistry.service.brreg;

import com.google.common.collect.ImmutableMap;
import no.difi.meldingsutveksling.serviceregistry.client.brreg.BrregClient;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import no.difi.meldingsutveksling.serviceregistry.service.brreg.dev.TestmiljøEnheter;
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
        Map<String, Optional<BrregEnhet>> brregMock = new TestmiljøEnheter().getTestMiljøEnheter();

        return orgnr -> brregMock.getOrDefault(orgnr, Optional.empty());
    }
}
