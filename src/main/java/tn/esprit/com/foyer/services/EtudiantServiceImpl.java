package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.repository.EtudiantRepository;
import tn.esprit.com.foyer.repository.ReservationRepository;
import tn.esprit.com.foyer.entity.Etudiant;
import tn.esprit.com.foyer.entity.Reservation;

import java.util.ArrayList;
import java.util.List;

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
