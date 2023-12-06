package tn.esprit.com.foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entity.Universite;
@Repository
public interface UniversiteRepository extends JpaRepository <Universite ,Long> {
    Universite findByNomUniversite(String nom);
}
