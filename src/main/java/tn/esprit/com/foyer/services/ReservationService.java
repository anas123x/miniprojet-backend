package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> retrieveAllReservations();

    Reservation addReservation(Reservation r);

    Reservation updateReservation(Reservation r);

    Reservation retrieveReservation(String idReservation);

    void removeReservation(String idReservation);
    List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut , Date dateFin );
}
