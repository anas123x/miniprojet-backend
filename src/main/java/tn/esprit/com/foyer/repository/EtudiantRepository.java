package tn.esprit.com.foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.com.foyer.entity.Etudiant;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant ,Long> {
    Etudiant findByNomEtAndPrenomEt(String nomEt , String prenomEt);

    Etudiant findByCin(long cinEtudiant);
}
