package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.*;

@Ignore
public class EduSerivceRecordTest {


    public static final String ENDPOINT_URL = "http://altinn.no";
    public static final String ORGNR = "123456789";

    @Test
    public void shouldTestCallToVirksertService() throws LookupException {

        VirkSertService virkSertService = Mockito.mock(VirkSertService.class);
        ELMALookupService elmaLookupService = Mockito.mock(ELMALookupService.class);


        Environment e = Mockito.mock(Environment.class);
        when(e.getProperty(PostVirksomhetServiceRecord.CONFIG_KEY)).thenReturn(ENDPOINT_URL);
        EDUServiceRecord eduServiceRecord = new EDUServiceRecord(e, virkSertService, elmaLookupService, ORGNR);
        eduServiceRecord.getEndPointURL();
        verify(elmaLookupService, times(1)).lookup("12asdasd")  ;

    }
}

