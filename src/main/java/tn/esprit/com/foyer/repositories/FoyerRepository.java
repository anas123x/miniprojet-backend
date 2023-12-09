package tn.esprit.com.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.com.foyer.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
