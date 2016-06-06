package no.difi.meldingsutveksling.serviceregistry.service.brreg;

import no.difi.meldingsutveksling.serviceregistry.client.brreg.BrregClientImpl;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class BrregServiceTest {

    @Test
    public void getOrgNameTest() {
        BrregEnhet enhet = new BrregEnhet();
        enhet.setOrganisasjonsnummer(991825827);
        enhet.setNavn("DIREKTORATET FOR FORVALTNING OG IKT");

        BrregClientImpl brregClientMock = Mockito.mock(BrregClientImpl.class);
        Mockito.when(brregClientMock.getBrregEnhetByOrgnr(Mockito.anyString())).thenReturn(null);
        Mockito.when(brregClientMock.getBrregEnhetByOrgnr("991825827")).thenReturn(enhet);

        BrregService brregService = new BrregService(brregClientMock);
        assertEquals("DIREKTORATET FOR FORVALTNING OG IKT", brregService.getOrgName("991825827"));
        assertEquals("", brregService.getOrgName("123"));
    }
}
