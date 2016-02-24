package no.difi.meldingsutveksling.serviceregistry.controller;


import no.difi.meldingsutveksling.serviceregistry.common.InfoRecord;
import no.difi.meldingsutveksling.serviceregistry.common.ServiceRecord;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.StringConvertingCertificateWrapper;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/organization")
@RestController
public class OrganisationController {

    public static final String NORWAY_PREFIX = "9908:";
    public static final String ALTINN =  "ALTINN";
    public static final String BESTEDU = "BEST/EDU";

    @Autowired
    VirkSertService virkSertService;

    @Autowired
    ELMALookupService elmaLookupSerice;

    @RequestMapping("/{orgnr}/info")
    public InfoRecord info(@PathVariable("orgnr") String orgnr) {
        InfoRecord record = new InfoRecord();
        try {
            virkSertService.getCertificate(orgnr);
            record.setCertificateAvailable(true);
        } catch (VirksertClientException e) {
            record.setCertificateAvailable(false);
        }
        try {
            elmaLookupSerice.lookup(NORWAY_PREFIX + orgnr);
            record.setSmpRecordAvailable(true);
        } catch (LookupException e) {
            record.setSmpRecordAvailable(false);
        }
        return record;
    }

    @RequestMapping("/{orgnr}/services")
    public List<ServiceRecord> services(@PathVariable("orgnr") String orgnr) {
        try {
            Certificate certificate = virkSertService.getCertificate(orgnr);
            Endpoint endPointInfo = elmaLookupSerice.lookup(NORWAY_PREFIX + orgnr);
            List<ServiceRecord> entries = new ArrayList<>();
            entries.add(new ServiceRecord(ALTINN, orgnr, StringConvertingCertificateWrapper.toString(certificate), BESTEDU, endPointInfo.getAddress()));
            return entries;
        } catch (LookupException | VirksertClientException e) {
            throw new NotFoundException(e);
        }
    }
}
