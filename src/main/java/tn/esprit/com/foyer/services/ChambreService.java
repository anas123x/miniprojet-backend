package tn.esprit.com.foyer.services;

import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entity.TypeChambrePourcentage;
import tn.esprit.com.foyer.entity.Chambre;

import java.util.HashSet;
import java.util.List;
@Service
public interface ChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    List<Chambre> getChambresParNomBloc(String nomBloc);
    //void pourcentageChambreParTypeChambre();

    //String calculerPourcentageChambreParTypeChambre(boolean estValide);

    HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValide);

}
