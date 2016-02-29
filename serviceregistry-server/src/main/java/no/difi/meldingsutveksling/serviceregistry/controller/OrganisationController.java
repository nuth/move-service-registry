package no.difi.meldingsutveksling.serviceregistry.controller;


import no.difi.meldingsutveksling.serviceregistry.common.InfoRecord;
import no.difi.meldingsutveksling.serviceregistry.common.Organization;
import no.difi.meldingsutveksling.serviceregistry.common.ServiceRecord;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.StringConvertingCertificateWrapper;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/organization")
@ExposesResourceFor(OrganizationResource.class)
@RestController
public class OrganisationController {

    public static final Map<String, String> primaryServiceMap = new HashMap<>();

    public static final String NORWAY_PREFIX = "9908:";
    public static final String EDU = "EDU";
    public static final String POST_TIL_VIRKSOMHET = "POST_TIL_VIRKSOMHET";
    public static final String FIKS = "FIKS";

    @Autowired
    VirkSertService virkSertService;

    @Autowired
    ELMALookupService elmaLookupSerice;


    @RequestMapping("/{orgnr}")
    @ResponseBody
    public HttpEntity<OrganizationResource> organisation(@PathVariable("orgnr") String orgnr) {

        Organization org = new Organization();
        try {
            org.addServiceRecord(createEDUServiceRecord(orgnr));
            org.addServiceRecord(createPostToCompanyServiceRecord(orgnr));
            org.addServiceRecord(createFiksService(orgnr));
            InfoRecord infoRecord = new InfoRecord(primaryServiceMap.get(orgnr) == null ? ""
                    : EDU.equals(primaryServiceMap.get(orgnr)) ? EDU
                    : FIKS.equals(primaryServiceMap.get(orgnr)) ? FIKS
                    : POST_TIL_VIRKSOMHET
                    , orgnr);

            infoRecord.setOrganisationNumber(orgnr);
            org.setInfo(infoRecord);

        } catch (LookupException e) {
            // nothing to do really
        } catch (VirksertClientException e) {
            // nothing to do really
        }
        OrganizationResource organizationRes = new OrganizationResource(org);
        return new ResponseEntity<>(organizationRes, HttpStatus.OK);
    }

    private ServiceRecord createEDUServiceRecord(String orgnr) throws VirksertClientException, LookupException {
        Certificate certificate = virkSertService.getCertificate(orgnr);
        Endpoint endPointInfo = elmaLookupSerice.lookup(NORWAY_PREFIX + orgnr);
        return new ServiceRecord(EDU, orgnr, StringConvertingCertificateWrapper.toString(certificate), EDU, endPointInfo.getAddress());
    }

    private ServiceRecord createPostToCompanyServiceRecord(String orgnr) throws VirksertClientException {
        Certificate certificate = virkSertService.getCertificate(orgnr);
        return new ServiceRecord(POST_TIL_VIRKSOMHET, orgnr, StringConvertingCertificateWrapper.toString(certificate), null, "http://postkasse.altinn.no");
    }

    private ServiceRecord createFiksService(String orgnr) throws VirksertClientException {
        Certificate certificate = virkSertService.getCertificate(orgnr);
        return new ServiceRecord(FIKS, " 971032146", StringConvertingCertificateWrapper.toString(certificate), null, "http://integrasjon.fiks.no");
    }

    @RequestMapping("/primary")
    public ResponseEntity setPrimary(@RequestParam("orgnr") String orgnr, @RequestParam("serviceidentifier") String serviceIdentifier) {
        primaryServiceMap.put(orgnr, serviceIdentifier);
        return new ResponseEntity(HttpStatus.OK);
    }
}
