package no.difi.meldingsutveksling.serviceregistry.common;

import java.util.ArrayList;
import java.util.List;


public class Organization {

    private InfoRecord info;
    private List<ServiceRecord> serviceRecords;

    public Organization() {
        serviceRecords = new ArrayList<>();
    }

    public void addServiceRecord(ServiceRecord s) {
        serviceRecords.add(s);
    }

    public InfoRecord getInfo() {
        return info;
    }

    public void setInfo(InfoRecord info) {
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
