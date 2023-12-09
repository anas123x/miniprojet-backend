package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.foyer.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
