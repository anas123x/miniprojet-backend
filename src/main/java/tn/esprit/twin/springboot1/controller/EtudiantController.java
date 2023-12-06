package tn.esprit.twin.springboot1.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot1.entity.Etudiant;
import tn.esprit.twin.springboot1.services.EtudiantService;
import tn.esprit.twin.springboot1.services.EtudiantServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantController {
    EtudiantService etudiantService;
    @GetMapping("/getalletudiants")
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    @GetMapping("/getetudiant/{etudiantid}")
    @Operation(description = "récupérer un étudiant par son id")
    public Etudiant retrieveEtudiant(@PathVariable("etudiantid") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }


    @PostMapping("/addetudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }
    @DeleteMapping("/removeetudiant/{etudiantid}")
    public void removeEtudiant(@PathVariable("etudiantid") Long etudiantId) {
        etudiantService.removeEtudiant(etudiantId);
    }
    @PutMapping("/updateetudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant= etudiantService.updateEtudiant(e);
        return etudiant;
    }
    @PutMapping("/affecter/{nomEt}/{prenomEt}/{idR}")
    public Etudiant affecteretudiant ( @PathVariable("nomEt")String nomEt,@PathVariable("prenomEt") String prenomEt,@PathVariable("idR") String idReservation){
        Etudiant etudiant = etudiantService.affecterEtudiantAReservation(nomEt,prenomEt,idReservation);
        return etudiant;
    }

}
