package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Reservation;
import tn.esprit.foyer.repositories.ReservationRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{
    ReservationRepository reservationRepository;
    @Override
    public Reservation updateReservation(Reservation res) {

        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(long idReservation) {
        return reservationRepository.findById(idReservation).get();
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaire(Date anneeUniversitaire) {
        List <Reservation> reservations = reservationRepository.findReservationByAnneeUniversitaire(anneeUniversitaire);

        return reservations;
    }

    @Override
    public Reservation addReservation(Reservation res) {
        Reservation reservation = reservationRepository.save(res);
        return reservation;
    }

    @Override
    public List<Reservation> retrieveReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations;
    }

    @Override
    public double statistiques() {
        List<Reservation> reservations = reservationRepository.findAll();
        // Computer les réservations valides
        long nombreReservationsValides = reservations.stream()
                .filter(reservation -> reservation.getEstValid() != null && !Boolean.FALSE.equals(reservation.getEstValid()))                .count();
        // Calculer le pourcentage
        double pourcentageValides = (nombreReservationsValides * 100.0) / reservations.size();

        return pourcentageValides;
    }

    @Override
    public void validerReservation(long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).get();
        reservation.setEstValid(true);
        reservationRepository.save(reservation);
    }


}