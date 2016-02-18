package no.difi.meldingsutveksling.adresseregister.controller;

import model.Entry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganisationController {

    @RequestMapping("/canreceive/{orgnr}")
    public void canReceive(@PathVariable("orgnr") String orgnr) {
        if (orgnr.contains("5")) {
            throw new NotFoundException();
        }
    }

    @RequestMapping("/info/{orgnr}")
    public List<Entry> info(@PathVariable("orgnr") String orgnr) {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(orgnr, "certificate", "bestedu", "https://tt02.basefarm.net"));
        entries.add(new Entry(orgnr, "cert", "posttilvirksomhet", "https://postkasse.basefarm.com"));
        return entries;
    }

}
