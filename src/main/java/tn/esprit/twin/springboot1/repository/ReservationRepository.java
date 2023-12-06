package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation , String> {
    Optional<Reservation> findById (String idReservation);
    List<Reservation> findByAnneeUniversitaireBetween(Date dateDebut , Date dateFin);

    //List<Reservation> findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(Long numeroChambre, boolean b, LocalDate dateStart, LocalDate dateEnd);
}
