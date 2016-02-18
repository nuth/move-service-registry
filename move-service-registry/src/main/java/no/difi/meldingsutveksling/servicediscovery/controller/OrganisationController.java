package no.difi.meldingsutveksling.servicediscovery.controller;


import no.difi.meldingsutveksling.servicediscovery.model.Entry;
import no.difi.meldingsutveksling.servicediscovery.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.servicediscovery.service.virksert.StringConvertingCertificateWrapper;
import no.difi.meldingsutveksling.servicediscovery.service.virksert.VirkSertService;
import no.difi.vefa.peppol.common.model.Endpoint;
import no.difi.vefa.peppol.lookup.api.LookupException;
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

    @Autowired
    VirkSertService virkSertService;

    @Autowired
    ELMALookupService elmaLookupSerice;

    @RequestMapping("/{orgnr}/canreceive")
    public void canReceive(@PathVariable("orgnr") String orgnr) {
        virkSertService.getCertificate(orgnr);
    }

    @RequestMapping("/{orgnr}/info")
    public List<Entry> info(@PathVariable("orgnr") String orgnr) {
        try {
            Certificate certificate = virkSertService.getCertificate(orgnr);
            Endpoint endPointInfo = elmaLookupSerice.lookup(NORWAY_PREFIX + orgnr);
            List<Entry> entries = new ArrayList<>();
            entries.add(new Entry(orgnr, StringConvertingCertificateWrapper.toString(certificate), "bestedu", endPointInfo.getAddress()));
            return entries;
        } catch (LookupException e) {
            throw new NotFoundException(e);
        }
    }
}
