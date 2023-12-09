package tn.esprit.foyer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Universite;

@Repository
public interface UniversteRepository extends JpaRepository<Universite, Long> {

    public Universite findByNomUniversite(String nomUniversite);

}