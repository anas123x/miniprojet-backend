package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Etudiant;
import tn.esprit.foyer.entities.Reservation;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(Etudiant e);

    Etudiant updateEtudiant(Etudiant e);

    Etudiant retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);

    Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, Long idReservation) ;

    public void passerUneReservation(long idEtudiant, Reservation res);

    public Etudiant findEtudiantwithemail(String email);

}
