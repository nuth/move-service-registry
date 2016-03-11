package no.difi.meldingsutveksling.serviceregistry;

public class ServiceDiscoveryException extends RuntimeException {
    public ServiceDiscoveryException(Exception e) {
        super(e);
    }

}