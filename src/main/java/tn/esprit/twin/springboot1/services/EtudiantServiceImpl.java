package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.entity.Etudiant;
import tn.esprit.twin.springboot1.entity.Foyer;
import tn.esprit.twin.springboot1.entity.Reservation;
import tn.esprit.twin.springboot1.entity.Universite;
import tn.esprit.twin.springboot1.repository.EtudiantRepository;
import tn.esprit.twin.springboot1.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).get();
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);

    }

    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation) {
        Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt,prenomEt);
        Reservation reservation = reservationRepository.findById(idReservation).get();
        List<Etudiant> etudiantmiseajour = new ArrayList<>();
        //attention il ya difference entre liste vide et null
        if (reservation.getEtudiants()!=null) {
            etudiantmiseajour = reservation.getEtudiants();
        }
        etudiantmiseajour.add(etudiant);
        reservation.setEtudiants(etudiantmiseajour);
        reservationRepository.save(reservation);
        return etudiant;
    }

}
