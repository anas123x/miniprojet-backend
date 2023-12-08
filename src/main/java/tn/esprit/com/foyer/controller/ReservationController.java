package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.services.IReservationService;


import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservation")
public class ReservationController {
    IReservationService reservationService;

    //Admin
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

    //Admin
    @GetMapping("/statistiques")
    public double statistiques(){
        double pourcentageValides = reservationService.statistiques();
        return pourcentageValides;
    }
    //Admin
    @GetMapping("/validerReservation/{idReservation}")
    public void validerReservation(@PathVariable("idReservation") long idReservation){
        reservationService.validerReservation(idReservation);
    }
}
