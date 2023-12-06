package tn.esprit.twin.springboot1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.springboot1.entity.Etudiant;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant ,Long> {
    Etudiant findByNomEtAndPrenomEt(String nomEt , String prenomEt);

    Etudiant findByCin(long cinEtudiant);
}
