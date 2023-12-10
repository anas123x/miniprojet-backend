package tn.esprit.com.foyer.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Etudiant;


import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.mailModel.MailStructure;
import tn.esprit.com.foyer.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EtudiantController {
    IEtudiantService etudiantService;

    @GetMapping("/retrieve-all-etudiant")
    List<Etudiant> retrieveAllEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    ;

    @PostMapping("/addetudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }

    ;

    @PutMapping("/updateetudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {

        Etudiant etudiant = etudiantService.updateEtudiant(e);
        return etudiant;
    }


    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    @DeleteMapping("/remouve-etudiant/{etudiant-id}")
    void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {

        etudiantService.removeEtudiant(etudiantId);
    }

    ;

    @PostMapping("/affecterEtudiantAReservation/{nom-et}/{prenom-et}/{id-reservation}")
    public Etudiant affecterEtudiantAReservation(@PathVariable("nom-et") String nomEt, @PathVariable("prenom-et") String prenomEt, @PathVariable("id-reservation") Long idReservation) {
        etudiantService.affecterEtudiantAReservation(nomEt, prenomEt, idReservation);
        return null;
    }

    @PostMapping("/passerUneReservation/{id-etudiant}")
    public void passerUneReservation(@PathVariable("id-etudiant") long idEtudiant , @RequestBody Reservation res){
        etudiantService.passerUneReservation(idEtudiant , res);
    }

    @GetMapping("/findEtudiantwithemail/{e-mail}")
    @JsonFormat
    public Etudiant findEtudiantwithemail(@PathVariable("e-mail") String email){
        Etudiant etudiant = etudiantService.findEtudiantwithemail(email);
        return etudiant;
    }


}
