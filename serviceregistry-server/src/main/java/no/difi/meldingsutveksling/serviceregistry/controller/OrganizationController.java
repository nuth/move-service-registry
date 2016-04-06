package no.difi.meldingsutveksling.serviceregistry.controller;


import no.difi.meldingsutveksling.serviceregistry.model.OrganizationInfo;
import no.difi.meldingsutveksling.serviceregistry.model.Organization;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.persistence.PrimaryServiceStore;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.meldingsutveksling.serviceregistry.servicerecord.EDUServiceRecord;
import no.difi.meldingsutveksling.serviceregistry.servicerecord.PostVirksomhetServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/organization")
@ExposesResourceFor(OrganizationResource.class)
@RestController
public class OrganizationController {

    private VirkSertService virkSertService;
    private ELMALookupService elmaLookupSerice;
    private KSLookup ksLookup;
    private PrimaryServiceStore store;

    @Autowired
    private Environment environment;

    @Autowired
    public OrganizationController(VirkSertService virkSertService, ELMALookupService elmaLookupSerice, KSLookup ksLookup, PrimaryServiceStore store) {
        this.virkSertService = virkSertService;
        this.elmaLookupSerice = elmaLookupSerice;
        this.ksLookup = ksLookup;
        this.store = store;
    }

    @RequestMapping("/{orgnr}")
    @ResponseBody
    public HttpEntity<OrganizationResource> organisation(@PathVariable("orgnr") String orgnr) {

        Organization org = new Organization();
        ServiceIdentifier identifier = store.getPrimaryServiceIdentifier(orgnr);
        final OrganizationInfo info = new OrganizationInfo(orgnr, identifier);
        org.setInfo(info);
        org.addServiceRecord(new EDUServiceRecord(environment, virkSertService, elmaLookupSerice, orgnr));
        org.addServiceRecord(new PostVirksomhetServiceRecord(environment, virkSertService, orgnr));
        OrganizationResource organizationRes = new OrganizationResource(org);
        return new ResponseEntity<>(organizationRes, HttpStatus.OK);
    }

    @RequestMapping("/primary")
    public ResponseEntity setPrimary(@RequestParam("orgnr") String orgnr, @RequestParam("serviceidentifier") String serviceIdentifier) {
        store.setPrimaryServiceIdentifier(orgnr, ServiceIdentifier.valueOf(serviceIdentifier));
        return new ResponseEntity(HttpStatus.OK);
    }
}
