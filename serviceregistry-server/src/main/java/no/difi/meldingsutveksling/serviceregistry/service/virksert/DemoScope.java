package no.difi.meldingsutveksling.serviceregistry.service.virksert;

import no.difi.certvalidator.api.CertificateBucketException;
import no.difi.certvalidator.util.KeyStoreCertificateBucket;
import no.difi.virksert.scope.AbstractScope;

public class DemoScope extends AbstractScope {

    @Override
    public KeyStoreCertificateBucket getKeyStore() throws CertificateBucketException {
        return new KeyStoreCertificateBucket(this.getClass().getResourceAsStream("/test-certificates.jks"), "changeit");
    }

    @Override
    public String[] getRootCertificateKeys() {
        return new String[]{"rootcert"};
    }

    @Override
    public String[] getIntermediateCertificateKeys() {
        return new String[]{"intermediate"};
    }
}
