package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Foyer;
@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
