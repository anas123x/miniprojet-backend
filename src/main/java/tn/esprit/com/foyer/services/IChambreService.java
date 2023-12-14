package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambre();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);

    public boolean isChambreDisponible(Long numeroChambre, TypeChambre typeC);
}
