package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier;

import java.io.Serializable;
import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ServiceRecord implements Serializable {

    private ServiceIdentifier serviceIdentifier;
    private String organisationNumber;

    public ServiceRecord(ServiceIdentifier serviceIdentifier, String organisationNumber) {
        this.organisationNumber = organisationNumber;
        this.serviceIdentifier = serviceIdentifier;
    }

    public ServiceRecord() {
    }

    public String getOrganisationNumber() {
        return organisationNumber;
    }

    public void setOrganisationNumber(String organisationNumber) {
        this.organisationNumber = organisationNumber;
    }

    public abstract String getX509Certificate();

    public abstract URL getEndPointURL();

    public ServiceIdentifier getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(ServiceIdentifier serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }


    @Override
    public String toString() {
        return "ServiceRecord{" +
                "serviceIdentifier='" + serviceIdentifier + '\'' +
                ", organisationNumber='" + organisationNumber + '\'' +
                ", x509Certificate='" + getX509Certificate() + '\'' +
                ", endPointURL='" + getEndPointURL().toString() + '\'' +
                '}';
    }
}
