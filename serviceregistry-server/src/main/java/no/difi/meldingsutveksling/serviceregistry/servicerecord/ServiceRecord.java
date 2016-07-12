package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import no.difi.meldingsutveksling.serviceregistry.ResourceNotFoundException;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.CertificateToString;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.virksert.client.VirksertClientException;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.security.cert.Certificate;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ServiceRecord implements Serializable {


    protected Environment environment;
    protected String organisationNumber;

    private VirkSertService virkSertService;
    private ServiceIdentifier serviceIdentifier;
    protected String endpointUrl;

    public ServiceRecord(Environment e, VirkSertService virkSertService, ServiceIdentifier serviceIdentifier, String organisationNumber) {
        this.organisationNumber = organisationNumber;
        this.serviceIdentifier = serviceIdentifier;
        this.virkSertService = virkSertService;
        this.environment = e;
    }

    public String getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(String organisationNumber) {
        this.organisationNumber = organisationNumber;
    }


    public abstract String getEndPointURL();

    public ServiceIdentifier getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(ServiceIdentifier serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }

    public String getPemCertificate() {
        try {
            Certificate c = virkSertService.getCertificate(getOrganisationNumber());
            return CertificateToString.toString(c);
        } catch (VirksertClientException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    @Override
    public String toString() {
        return "ServiceRecord{" +
                "serviceIdentifier='" + serviceIdentifier + '\'' +
                ", organisationNumber='" + organisationNumber + '\'' +
                ", pemCertificate='" + getPemCertificate() + '\'' +
                ", endPointURL='" + getEndPointURL() + '\'' +
                '}';
    }
}
