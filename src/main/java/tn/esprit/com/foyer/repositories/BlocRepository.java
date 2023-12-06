package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Etat;

import java.util.List;


public interface BlocRepository extends JpaRepository<Bloc,Long> {

    @Query("SELECT COUNT(b) FROM Bloc b WHERE b.etat = :etat")
    Float nbrBlocParEtat(@Param("etat") Etat etat);

    public Bloc findByNomBloc(String nom);
    // Méthode pour trouver tous les blocs dont l'état est MAUVAIS
    List<Bloc> findByEtat(Etat etat);
}
