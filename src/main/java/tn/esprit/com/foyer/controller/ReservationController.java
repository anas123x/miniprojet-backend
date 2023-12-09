package tn.esprit.com.foyer.controller;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.services.IReservationService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    IReservationService reservationService;
    @GetMapping("/retrieveReservations")
    public List<Reservation> retrieveReservations(){
        List<Reservation> reservations = reservationService.retrieveReservations();
        return reservations;
    }
    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation res){
        Reservation reservation = reservationService.addReservation(res);
        return reservation;
    }

    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation res){
        Reservation reservation =  reservationService.updateReservation(res);
        return  reservation;
    }

    @GetMapping("/retrieveReservation/{id-reservation}")
    public Reservation retrieveReservation(@PathVariable("id-reservation") long idReservation){
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        return reservation;
    }

    @GetMapping("/getReservationParAnneeUniversitaire/{annee-univ}")
    public List <Reservation> getReservationParAnneeUniversitaire(@PathVariable("annee-univ") Date anneeUniversitaire){
        List <Reservation> reservations= reservationService.getReservationParAnneeUniversitaire(anneeUniversitaire);
        return reservations;
    }

    @GetMapping("/statistiques")
    public double statistiques(){
        double pourcentageValides = reservationService.statistiques();
        return pourcentageValides;
    }

    @PutMapping("/validerReservation/{id-reservation}")
    public void validerReservation(@PathVariable("id-reservation") long idReservation){
        reservationService.validerReservation(idReservation);
    }
}