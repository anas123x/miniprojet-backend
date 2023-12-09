package tn.esprit.com.foyer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entities.Universite;

@Repository
public interface UniversteRepository extends JpaRepository<Universite, Long> {

    public Universite findByNomUniversite(String nomUniversite);

}