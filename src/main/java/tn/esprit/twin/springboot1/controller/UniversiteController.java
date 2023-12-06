package tn.esprit.twin.springboot1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot1.entity.Universite;
import tn.esprit.twin.springboot1.services.UniversiteService;
import tn.esprit.twin.springboot1.services.UniversiteServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {
    UniversiteService universiteService;

    @GetMapping("retrievealluniversites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversities();
        return listUniversites;
    }

    @GetMapping("/retrieveuniversite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long universiteId) {
        return universiteService.retrieveUniversity(universiteId);
    }


    @PostMapping("adduniversite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversity(u);
        return universite;
    }

    @DeleteMapping("/removeuniversite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long universiteId) {
        universiteService.removeUniversity(universiteId);
    }

    @PutMapping("updateUniversite")
    public Universite updateUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.updateUniversity(u);
        return universite;
    }
    @PutMapping("/affecter/{idFoyer}/{nomuniversite}")
    public  Universite affecterFoyerAUniversite(@PathVariable("idFoyer") Long idFoyer , @PathVariable("nomuniversite") String nomUniversite ) {
        Universite universite = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
        return universite;
    }
    @PutMapping("/desaffecter/{iduniversite}")
    public Universite desaffecter(@PathVariable("iduniversite") Long idUniversite) {
        Universite universite = universiteService.desaffecterFoyerAUniversite(idUniversite);
        return universite;
    }



}
















