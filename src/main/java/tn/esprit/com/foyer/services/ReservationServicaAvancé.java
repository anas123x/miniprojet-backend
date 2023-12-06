package tn.esprit.com.foyer.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.repository.BlocRepository;
import tn.esprit.com.foyer.repository.ChambreRepository;
import tn.esprit.com.foyer.repository.EtudiantRepository;
import tn.esprit.com.foyer.repository.ReservationRepository;
import tn.esprit.com.foyer.CapaciteMaxAtteinteException;
import tn.esprit.com.foyer.entity.Bloc;
import tn.esprit.com.foyer.entity.Chambre;
import tn.esprit.com.foyer.entity.Etudiant;
import tn.esprit.com.foyer.entity.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Slf4j
@Service
public class ReservationServicaAvancé {
       ChambreRepository chambreRepository;
        EtudiantRepository etudiantRepository;
        ReservationRepository reservationRepository;
        BlocRepository blocRepository ;
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        int annee = LocalDate.now().getYear();

        // Récupérer le bloc correspondant à l'identifiant fourni
        Bloc bloc = blocRepository.findByIdBloc(idBloc);

        // Vérifier si le bloc existe
        if (bloc == null) {
            throw new EntityNotFoundException("Bloc non trouvé");
        }

        List<Chambre> chambres = bloc.getChambre();
        if (chambres.isEmpty()) {
            throw new EntityNotFoundException("Aucune chambre trouvée dans le bloc");
        }
        Chambre chambre = chambres.get(0);

        // Récupérer l'étudiant correspondant au CIN
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        // Vérifier si l'étudiant existe
        if (etudiant == null) {
            throw new EntityNotFoundException("Étudiant non trouvé");
        }

        // Vérifier si la capacité maximale de la chambre n'est pas atteinte
        if (capaciteMaxNonAtteinte(chambre)) {

            // Construire le numéro de réservation selon le format spécifié
            String numReservation = construireNumeroReservation(chambre);

            // Créer la réservation avec les conditions spécifiées
            Reservation reservation = new Reservation();
            reservation.setNumReservation(numReservation);
            reservation.setAnneeUniversitaire(new Date(annee));
            reservation.setEstValide(true);

            // Sauvegarder la réservation dans la base de données
            reservation = reservationRepository.save(reservation);

            // Associer la réservation à la chambre et à l'étudiant
            chambre.getReservations().add(reservation);
            etudiant.getReservations().add(reservation);

            // Mettre à jour la chambre et l'étudiant dans la base de données (à adapter selon votre logique)
            chambreRepository.save(chambre);
            etudiantRepository.save(etudiant);

            return reservation;
        } else {
            throw new CapaciteMaxAtteinteException("La capacité maximale de la chambre est atteinte");
        }
    }


    private boolean capaciteMaxNonAtteinte(Chambre chambre) {
            int capaciteMax = 0;

            // Déterminez la capacité maximale en fonction du type de chambre
            switch (chambre.getTypeC()) {
                case SIMPLE:
                    capaciteMax = 1;
                    break;
                case DOUBLE:
                    capaciteMax = 2;
                    break;
                case TRIPLE:
                    capaciteMax = 3;
                    break;
            }

            // Vérifiez si la capacité maximale n'est pas atteinte
            return chambre.getReservations().size() < capaciteMax;
        }

        private String construireNumeroReservation(Chambre chambre) {
            int annee = LocalDate.now().getYear();
            // Récupérer les informations nécessaires de la chambre
            Long numeroChambre = chambre.getNumeroChambre();
            Long idBloc = chambre.getBloc().getIdBloc();

            // Construire le numéro de réservation selon le format spécifié
            return String.format("%d-%d-%s",
                    numeroChambre, idBloc, new Date(annee)); // Remplacez "2023" par l'année universitaire réelle
        }
    }


