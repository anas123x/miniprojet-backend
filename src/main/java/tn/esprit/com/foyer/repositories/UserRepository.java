package tn.esprit.com.foyer.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.com.foyer.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
