package tn.esprit.foyer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findReservationByAnneeUniversitaire(Date anneuniv);
}