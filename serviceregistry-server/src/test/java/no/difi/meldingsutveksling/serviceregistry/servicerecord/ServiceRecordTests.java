package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.ks.MockKSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceRecordTests {

    private static final String ENDPOINT_URL = "http://altinn.no";
    private static final String ORGNR = "123456789";

    @Mock
    private VirkSertService virkSertService;

    @Mock
    private ELMALookupService elmaLookupService;

    @Mock
    private Environment environment;

    @InjectMocks
    private EDUServiceRecord eduServiceRecord;

    @InjectMocks
    private PostVirksomhetServiceRecord postVirksomhetServiceRecord;

    @Spy
    private KSLookup ksLookup = new MockKSLookup();

    @Before
    public void spyKsLookup() {
        when(ksLookup.mapOrganizationNumber(anyString())).thenCallRealMethod();
    }

    @After
    public void tearDown() {
        postVirksomhetServiceRecord = null;
        eduServiceRecord = null;
    }

    @Test
    public void testShouldGetEndPointFromEDUServiceRecord() throws LookupException {
        // The EDURecord should lookup the Endpoint Using ELMA
        eduServiceRecord.setOrganisationNumber(ORGNR);
        when(elmaLookupService.lookup("9908:" + ORGNR)).thenReturn(new Endpoint(null, null, ENDPOINT_URL, null));
        assertEquals(ENDPOINT_URL, eduServiceRecord.getEndPointURL());
    }

    @Test
    public void testShouldgetEndPointForPostVirksomhetService() throws LookupException {
        // But ... The PostVirksomhetRecord should lookup the Endpoint from configuration file
        when(environment.getProperty(PostVirksomhetServiceRecord.CONFIG_KEY_ENDPOINT)).thenReturn(ENDPOINT_URL);
        assertEquals(ENDPOINT_URL, postVirksomhetServiceRecord.getEndPointURL());
    }

    @Test
    public void testShouldSwapCertificateAndOrgNumberForKSManagedEDU() {
        eduServiceRecord.setOrganisationNumber(MockKSLookup.KS_MANAGED_1);
        assertEquals(MockKSLookup.KS_ORGNR, eduServiceRecord.getOrganisationNumber());
    }
}