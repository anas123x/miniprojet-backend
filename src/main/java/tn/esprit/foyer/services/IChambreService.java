package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambrePourcentage;

import java.util.HashSet;
import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambre();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValid);

}
