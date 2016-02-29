package no.difi.meldingsutveksling.serviceregistry.client;

import no.difi.meldingsutveksling.serviceregistry.common.Organization;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 *
 */
public class ServiceRegistryClient {

    public static final String DIFI = "991825827";

    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        Organization org = restTemplate.getForObject("http://localhost:2222/organization/910094092", Organization.class);
        System.out.println(org);

    }

}
