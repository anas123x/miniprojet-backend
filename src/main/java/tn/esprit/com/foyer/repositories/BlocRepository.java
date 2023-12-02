package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;

import java.util.List;


public interface BlocRepository extends JpaRepository<Bloc,Long> {
    public Bloc findByNomBloc(String nom);
    @Query("SELECT COUNT(c) FROM Chambre c WHERE c.typeC = :type AND c.bloc.idBloc = :idBloc")
    long nbChambreParTypeEtBloc(@Param("type") TypeChambre type, @Param("idBloc") long idBloc);


    Bloc findByIdBloc(long idBloc);

    List<Bloc> findByFoyer(Foyer foyer);
}
