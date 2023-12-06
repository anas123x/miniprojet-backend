package tn.esprit.twin.springboot1.services;

import tn.esprit.twin.springboot1.entity.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);
    Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, String idReservation) ;
}
