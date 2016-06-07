package no.difi.meldingsutveksling.serviceregistry.service.brreg;

import no.difi.meldingsutveksling.serviceregistry.client.brreg.BrregClientImpl;
import no.difi.meldingsutveksling.serviceregistry.model.BrregEnhet;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class BrregServiceTest {

    @Test
    public void getOrgNameTest() {
        String orgNavn = "DIREKTORATET FOR FORVALTNING OG IKT";
        int orgNr = 991825827;

        BrregEnhet enhet = new BrregEnhet();
        enhet.setOrganisasjonsnummer(orgNr);
        enhet.setNavn(orgNavn);

        BrregClientImpl brregClientMock = Mockito.mock(BrregClientImpl.class);
        Mockito.when(brregClientMock.getBrregEnhetByOrgnr(Mockito.anyString())).thenReturn(null);
        Mockito.when(brregClientMock.getBrregEnhetByOrgnr(String.valueOf(orgNr))).thenReturn(enhet);

        BrregService brregService = new BrregService(brregClientMock);
        assertEquals(orgNavn, brregService.getOrgName(String.valueOf(orgNr)));
        assertEquals("", brregService.getOrgName("123"));
    }
}
