package no.difi.meldingsutveksling.serviceregistry.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.difi.meldingsutveksling.serviceregistry.common.InfoRecord;
import no.difi.meldingsutveksling.serviceregistry.common.Organization;
import no.difi.meldingsutveksling.serviceregistry.common.ServiceRecord;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OrganizationResource extends ResourceSupport {

    private String organizationNumber;
    private String primaryServiceIdentifier;
    private List<ServiceRecordResource> serviceRecords;

    @JsonCreator
    public OrganizationResource(@JsonProperty("organization") Organization organization) {
        this.organizationNumber = organization.getInfo().getOrganisationNumber();
        this.primaryServiceIdentifier = organization.getInfo().getPrimaryServiceIdentifier();

        serviceRecords = new ArrayList<>();
        for (ServiceRecord r : organization.getServiceRecords()) {
            final ServiceRecordResource e = new ServiceRecordResource(r);
            final Object invocationValue = methodOn(OrganisationController.class).setPrimary(organization.getInfo().getOrganisationNumber(),
                    r.getServiceIdentifier());
            e.add(linkTo(invocationValue).withRel("setprimary"));
            e.add(new Link("http://www.vg.on"));
            serviceRecords.add(e);
        }
    }

    public String getOrganizationNumber() {
        return organizationNumber;
    }

    public void setOrganizationNumber(String organizationNumber) {
        this.organizationNumber = organizationNumber;
    }

    public List<ServiceRecordResource> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecordResource> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    public void setPrimaryServiceIdentifier(String primaryServiceIdentifier) {
        this.primaryServiceIdentifier = primaryServiceIdentifier;
    }

    public String getPrimaryServiceIdentifier() {
        return primaryServiceIdentifier;
    }
}

