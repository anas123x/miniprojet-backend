package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.repositories.EtudiantRepository;
import tn.esprit.com.foyer.repositories.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantService implements IEtudiantService{
    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;
    ReservationService reservationService;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {

        return etudiantRepository.save(e) ;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        log.info("azedaze");
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
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, Long idReservation) {
        Etudiant etudiant = etudiantRepository.findEtudiantByNomEtAndPrenomEt(nomEt, prenomEt);
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        List<Reservation> reservations = new ArrayList<>();
        if (etudiant.getReservations() != null){
            reservations = etudiant.getReservations();
        }
        reservations.add(reservation);
        etudiant.setReservations(reservations);
        etudiantRepository.save(etudiant);
        return etudiant;
    }

    @Override
    public void passerUneReservation(long idEtudiant , Reservation res) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).get();
        Reservation reservation = reservationService.addReservation( res);
        List<Reservation> reservations = new ArrayList<>();
        if (etudiant.getReservations() != null){
            reservations = etudiant.getReservations();
        }
        reservations.add(reservation);
        etudiant.setReservations(reservations);
        etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant findEtudiantwithemail(String email) {
        Etudiant etudiant = etudiantRepository.findEtudiantByEmail(email);
        return etudiant;
    }




}