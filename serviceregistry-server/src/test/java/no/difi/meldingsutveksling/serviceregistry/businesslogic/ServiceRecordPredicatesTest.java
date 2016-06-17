package no.difi.meldingsutveksling.serviceregistry.businesslogic;

import no.difi.meldingsutveksling.serviceregistry.model.OrganizationInfo;
import no.difi.meldingsutveksling.serviceregistry.model.OrganizationType;
import no.difi.meldingsutveksling.serviceregistry.model.OrganizationTypes;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ServiceRecordPredicatesTest {

    @Test
    public void messagesToPrivateOrganizationsUsesPostTilVirksomhet() {
        final OrganizationInfo privateOrganization = privateOrganization();

        final boolean result = ServiceRecordPredicates.usesPostTilVirksomhet().test(privateOrganization);

        assertThat("Private organization should use post til virksomheter", result, equalTo(true));
    }

    @Test
    public void messagesToPublicOrganizationsUsesFormidlingstjenesten() {
        final OrganizationInfo orgl = publicOrganization();

        final boolean result = ServiceRecordPredicates.usesFormidlingstjenesten().test(orgl);

        assertThat("Public organization should use formidlingstjenesten", result, equalTo(true));
    }

    private OrganizationInfo privateOrganization() {
        final Optional<OrganizationType> privateType = new OrganizationTypes().privateOrganization().stream().findFirst();
        return new OrganizationInfo.Builder().setOrganizationType(privateType.get()).build();
    }

    private OrganizationInfo publicOrganization() {
        final Optional<OrganizationType> publicType = new OrganizationTypes().publicOrganization().stream().findFirst();
        return new OrganizationInfo.Builder().setOrganizationType(publicType.get()).build();
    }
}