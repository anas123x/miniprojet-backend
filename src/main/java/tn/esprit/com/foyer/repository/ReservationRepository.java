package tn.esprit.com.foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entity.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation , String> {
    Optional<Reservation> findById (String idReservation);
    List<Reservation> findByAnneeUniversitaireBetween(Date dateDebut , Date dateFin);

    //List<Reservation> findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(Long numeroChambre, boolean b, LocalDate dateStart, LocalDate dateEnd);
}
