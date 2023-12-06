package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entity.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);
    Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, String idReservation) ;
}
