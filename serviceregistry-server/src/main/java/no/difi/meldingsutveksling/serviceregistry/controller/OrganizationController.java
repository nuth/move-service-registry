package no.difi.meldingsutveksling.serviceregistry.controller;


import no.difi.meldingsutveksling.serviceregistry.exceptions.EndpointUrlNotFound;
import no.difi.meldingsutveksling.serviceregistry.model.Organization;
import no.difi.meldingsutveksling.serviceregistry.model.OrganizationInfo;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import no.difi.meldingsutveksling.serviceregistry.service.brreg.BrregService;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.ks.KSLookup;
import no.difi.meldingsutveksling.serviceregistry.service.persistence.PrimaryServiceStore;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.meldingsutveksling.serviceregistry.servicerecord.ServiceRecordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static no.difi.meldingsutveksling.serviceregistry.businesslogic.ServiceRecordPredicates.usesFormidlingstjenesten;
import static no.difi.meldingsutveksling.serviceregistry.businesslogic.ServiceRecordPredicates.usesPostTilVirksomhet;

@RequestMapping("/organization")
@ExposesResourceFor(OrganizationResource.class)
@RestController
public class OrganizationController {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
    private final ServiceRecordFactory serviceRecordFactory;
    private BrregService brregService;
    private PrimaryServiceStore store;

    @Autowired
    private Environment environment;

    /**
     *
     * @param virkSertService used to retrieve organization certificates
     * @param elmaLookupSerice used to lookup urls
     * @param ksLookup used for KS transport
     * @param store used to persist internal state
     * @param brregService needed to lookup and retrieve organization information using an organization number
     */
    @Autowired
    public OrganizationController(VirkSertService virkSertService, ELMALookupService elmaLookupSerice, KSLookup ksLookup, PrimaryServiceStore store, BrregService brregService) {
        this.brregService = brregService;
        this.store = store;
        this.serviceRecordFactory = new ServiceRecordFactory(environment, virkSertService, elmaLookupSerice, ksLookup);
    }

    /**
     * Used to retrieve information needed to send a message to an organization having the provided orgnr
     * @param orgnr of the organization to receive a message
     * @return JSON object with information needed to send a message
     */
    @RequestMapping("/{orgnr}")
    @ResponseBody
    public HttpEntity<OrganizationResource> organisation(@PathVariable("orgnr") String orgnr) {

        Organization org = new Organization();
        ServiceIdentifier identifier = store.getPrimaryOverride(orgnr);
        OrganizationInfo organization = brregService.getOrganizationInfo(orgnr);
        organization.setPrimaryServiceIdentifier(identifier);

        if(usesFormidlingstjenesten().test(organization)) {
            org.addServiceRecord(serviceRecordFactory.createEduServiceRecord(orgnr));
        }
        if(usesPostTilVirksomhet().test(organization)) {
            org.addServiceRecord(serviceRecordFactory.createPostVirksomhetServiceRecord(orgnr));
        }
        org.setInfo(organization);
        OrganizationResource organizationRes = new OrganizationResource(org);
        return new ResponseEntity<>(organizationRes, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find endpoint url for service of requested organization")
    @ExceptionHandler(EndpointUrlNotFound.class)
    public void notFound(HttpServletRequest req, Exception e) {
        logger.info(String.format("Endpoint not found for %s", req.getRequestURL()), e);
    }


    @RequestMapping("/primary")
    public ResponseEntity setPrimary(@RequestParam("orgnr") String orgnr, @RequestParam("serviceidentifier") String serviceIdentifier) {
        store.setPrimaryOverride(orgnr, ServiceIdentifier.valueOf(serviceIdentifier));
        return new ResponseEntity(HttpStatus.OK);
    }
}
