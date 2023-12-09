package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Bloc;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre , Long> {
    Chambre findByNumeroChambre (Long numChambre);
    List<Chambre> findByBlocIdBloc(Long idBloc);
    @Query("SELECT c FROM Chambre c WHERE c.bloc.nomBloc = :nomBloc")
    List<Chambre> getChambresParNomBloc(@Param("nomBloc") String nomBloc);

    /*@Query("SELECT count(*) FROM Chambre c WHERE c.typeC =:type ")
    Float nbrchambrepartype (@Param("type") TypeChambre t);*/
    @Query("SELECT COUNT(c) FROM Chambre c " +
            "LEFT JOIN c.reservations r " +
            "WHERE c.typeC = :type AND (:estReservee IS NULL OR r.estValid = :estReservee)")
    Float nbrChambreParType(@Param("type") TypeChambre type, @Param("estReservee") Boolean estReservee);

    @Query("SELECT c.bloc.capaciteBloc FROM Chambre c WHERE c = :chambre")
    Long obtenirCapaciteBlocParChambre(@Param("chambre") Chambre chambre);

    List<Chambre> findByBloc(Bloc bloc);
}
