package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entity.Reservation;
import tn.esprit.com.foyer.services.ReservationServicaAvancé;
import tn.esprit.com.foyer.services.ReservationService;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
    ReservationService reservationService;

    ReservationServicaAvancé reservationServicaAvancé;
    @GetMapping("getallreservations")
    public List<Reservation> getReservation() {
        List<Reservation> listReservation = reservationService.retrieveAllReservations();
        return listReservation;
    }

    @GetMapping("/getreservation/{reservationid}")
    public Reservation retrieveReservation(@PathVariable("reservationid") String IdReservation) {
        return reservationService.retrieveReservation(IdReservation);
    }


    @PostMapping("addreservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    @DeleteMapping("/removereservation/{reservationid}")
    public void removeReservation(@PathVariable("reservationid")String IdReservation) {
        reservationService.removeReservation(IdReservation);
    }

    @PutMapping("updatecreservation")
    public Reservation updateReservation(@RequestBody Reservation ch) {
        Reservation reservation = reservationService.updateReservation(ch);
        return reservation;
    }
    @GetMapping("/paranneeuniversitaire")
    public List<Reservation> getReservationParAnneeUniversitaire(
            @RequestParam("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin
    ) {
        return reservationService.getReservationParAnneeUniversitaire(dateDebut, dateFin);
    }

    /*@PostMapping("/add")
    public Reservation addReservation(@RequestParam long idBloc, @RequestParam long cin) {
        return reservationServicaAvancé.ajouterReservation(idBloc, cin);
    }*/

    @PostMapping("/addres")
    public Reservation ajouterReservation(@RequestParam long idBloc, @RequestParam long cin) {
            Reservation reservation = reservationServicaAvancé.ajouterReservation(idBloc, cin);
            return reservation;
    }
}
