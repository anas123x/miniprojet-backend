package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Universite;
@Repository
public interface UniversiteRepository extends JpaRepository <Universite ,Long> {
    Universite findByNomUniversite(String nom);
}
