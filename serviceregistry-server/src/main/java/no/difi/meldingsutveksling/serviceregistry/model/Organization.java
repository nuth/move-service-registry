package no.difi.meldingsutveksling.serviceregistry.model;


import no.difi.meldingsutveksling.serviceregistry.servicerecord.ServiceRecord;

import java.util.ArrayList;
import java.util.List;


public class Organization {

    private OrganizationInfo info;
    private List<ServiceRecord> serviceRecords;

    public Organization() {
        serviceRecords = new ArrayList<>();
    }

    public void addServiceRecord(ServiceRecord s) {
        serviceRecords.add(s);
    }

    public OrganizationInfo getInfo() {
        return info;
    }

    public void setInfo(OrganizationInfo info) {
        this.info = info;
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "info=" + info +
                ", serviceRecords=" + serviceRecords +
                '}';
    }
}
