package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.mailModel.MailStructure;

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

