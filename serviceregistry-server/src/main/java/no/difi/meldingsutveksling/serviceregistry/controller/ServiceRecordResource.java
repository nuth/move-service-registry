package no.difi.meldingsutveksling.serviceregistry.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.difi.meldingsutveksling.serviceregistry.common.ServiceRecord;
import org.springframework.hateoas.ResourceSupport;

public class ServiceRecordResource extends ResourceSupport {
    private ServiceRecord serviceRecord;

    @JsonCreator
    public ServiceRecordResource(@JsonProperty("servicerecord") ServiceRecord serviceRecord) {
        this.serviceRecord = serviceRecord;

    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }
}
