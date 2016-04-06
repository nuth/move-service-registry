package no.difi.meldingsutveksling.serviceregistry.controller;

import no.difi.meldingsutveksling.serviceregistry.MoveServiceRegistryApplication;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.persistence.PrimaryServiceStore;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MoveServiceRegistryApplication.class)
@WebAppConfiguration
@Ignore
public class OrganizationControllerTest {

    public static final String KS = "0123456789";
    public static final String BERGEN_KOMMUNE = "9876543210";
    public static final String DIFI = "11111111";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ELMALookupService elmaLookupService;
    private KSLookup ksLookup;
    private VirkSertService virkSertService;
    private PrimaryServiceStore primaryServiceStore;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        setupMocks();

        when(elmaLookupService.lookup(KS)).thenReturn(new Endpoint(null, null, "http://altinn.no", null));

        //9876543210 is managed by KS
        when(ksLookup.isKSManaged(BERGEN_KOMMUNE)).thenReturn(true);
        when(ksLookup.mapOrganizationNumber(BERGEN_KOMMUNE)).thenReturn(KS);

        when(virkSertService.getCertificate(KS)).thenReturn(null);
        when(primaryServiceStore.getPrimaryServiceIdentifier(KS)).thenReturn(ServiceIdentifier.EDU);
    }

    private void setupMocks() {
        elmaLookupService = mock(ELMALookupService.class);
        ksLookup = mock(KSLookup.class);
        virkSertService = mock(VirkSertService.class);
        primaryServiceStore = mock(PrimaryServiceStore.class);
    }


    @Test
    public void testShouldListServiceRecords() {


    }


}
