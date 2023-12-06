package tn.esprit.twin.springboot1.services;

import tn.esprit.twin.springboot1.entity.Chambre;
import tn.esprit.twin.springboot1.entity.Reservation;

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
