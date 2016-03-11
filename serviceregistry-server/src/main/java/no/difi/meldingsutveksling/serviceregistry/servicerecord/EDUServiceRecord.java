package no.difi.meldingsutveksling.serviceregistry.servicerecord;

import no.difi.meldingsutveksling.serviceregistry.ServiceRegistryException;
import no.difi.meldingsutveksling.serviceregistry.service.elma.ELMALookupService;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.StringConvertingCertificateWrapper;
import no.difi.meldingsutveksling.serviceregistry.service.virksert.VirkSertService;
import no.difi.vefa.peppol.lookup.api.LookupException;
import no.difi.virksert.client.VirksertClientException;

import static no.difi.meldingsutveksling.serviceregistry.model.ServiceIdentifier.EDU;

public class EDUServiceRecord extends ServiceRecord {

    private VirkSertService virkSertService;

    private ELMALookupService elmaLookupService;

    public EDUServiceRecord(VirkSertService virkSertService, ELMALookupService elmaLookupService, String orgnr) {
        super(EDU, orgnr);
        this.virkSertService = virkSertService;
        this.elmaLookupService = elmaLookupService;
    }

    @Override
    public String getX509Certificate() {
        try {
            return StringConvertingCertificateWrapper.toString(
                    virkSertService.getCertificate(getOrganisationNumber()));
        } catch (VirksertClientException e) {
            throw new ServiceRegistryException(e);
        }
    }

    @Override
    public String getEndPointURL() {
        try {
            return elmaLookupService.lookup(getOrganisationNumber()).getAddress() ;
        } catch (LookupException e) {
            throw new ServiceRegistryException(e);
        }
    }
}
