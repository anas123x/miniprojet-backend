package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Etat;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Foyer;

import java.util.List;
import java.util.Set;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {


    @Query("SELECT COUNT(b) FROM Bloc b WHERE b.foyer.idFoyer = :foyerId AND b.etat = 'mauvaise'")
    int countBlocsWithBadCondition(@Param("foyerId") Long foyerId);

    @Query("SELECT COUNT(b) FROM Bloc b WHERE b.foyer.idFoyer = :foyerId")
    int countTotalBlocsInFoyer(@Param("foyerId") Long foyerId);

    @Query("SELECT SUM(b.capaciteBloc) FROM Foyer f JOIN f.bloc b WHERE f.idFoyer = :idFoyer")
    Long getTotalCapaciteFoyer(@Param("idFoyer") Long idFoyer);
    @Query("SELECT f.idFoyer, b.etat, SUM(b.capaciteBloc) FROM Foyer f JOIN f.bloc b GROUP BY f.idFoyer, b.etat")
    List<Object[]> calculateCapacitePercentageByEtat();
    @Query("SELECT DISTINCT e FROM Foyer f LEFT JOIN f.bloc b LEFT JOIN b.chambres c LEFT JOIN c.reservations res LEFT JOIN res.etudiants e WHERE f.idFoyer = :foyerId")
    Set<Etudiant> findEtudiantsByFoyerId(@Param("foyerId") Long foyerId);
}
