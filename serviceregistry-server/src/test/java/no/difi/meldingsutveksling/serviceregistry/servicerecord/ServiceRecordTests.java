package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.ks.MockKSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ServiceRecordTests {


    public static final String ENDPOINT_URL = "http://altinn.no";
    public static final String ORGNR = "123456789";

    @Test
    public void testShouldGetEndPointFromEDUServiceRecord() throws LookupException {

        VirkSertService virkSertService = Mockito.mock(VirkSertService.class);
        ELMALookupService elmaLookupService = Mockito.mock(ELMALookupService.class);
        Environment e = Mockito.mock(Environment.class);

        KSLookup ksLookup = new MockKSLookup();
        // The EDURecord should lookup the Endpoint Using ELMA
        when(elmaLookupService.lookup("9908:" + ORGNR)).thenReturn(new Endpoint(null, null, ENDPOINT_URL, null));

        EDUServiceRecord eduServiceRecord = new EDUServiceRecord(e, virkSertService, elmaLookupService, ksLookup, ORGNR);
        assertEquals(ENDPOINT_URL, eduServiceRecord.getEndPointURL());
    }

    @Test
    public void testShouldgetEndPointForPostVirksomhetService() throws LookupException {

        VirkSertService virkSertService = Mockito.mock(VirkSertService.class);
        ELMALookupService elmaLookupService = Mockito.mock(ELMALookupService.class);
        Environment e = Mockito.mock(Environment.class);

        // The EDURecord should lookup the Endpoint from configuration file
        when(e.getProperty(PostVirksomhetServiceRecord.CONFIG_KEY_ENDPOINT)).thenReturn(ENDPOINT_URL);

        PostVirksomhetServiceRecord postVirksomhetServiceRecord =
                new PostVirksomhetServiceRecord(e, virkSertService, ORGNR);

        assertEquals(ENDPOINT_URL, postVirksomhetServiceRecord.getEndPointURL());
    }


    @Test
    public void testShouldSwapCertificateAndOrgNumberForKSManagedEDU() {

        VirkSertService virkSertService = Mockito.mock(VirkSertService.class);
        ELMALookupService elmaLookupService = Mockito.mock(ELMALookupService.class);
        KSLookup ksLookup = new MockKSLookup();
        Environment e = Mockito.mock(Environment.class);

        EDUServiceRecord eduServiceRecord = new EDUServiceRecord(e, virkSertService, elmaLookupService, ksLookup, MockKSLookup.KS_MANAGED_1);
        assertEquals(MockKSLookup.KS_ORGNR, eduServiceRecord.getOrganisationNumber());
    }

}


