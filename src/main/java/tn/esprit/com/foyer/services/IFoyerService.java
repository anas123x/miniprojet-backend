package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer (Foyer f);
    Foyer updateFoyer (Foyer f);
    Foyer retrieveFoyer (long idFoyer);
    void archiverFoyer (long idFoyer);
    void deleteFoyer(long idFoyer);

    public Set<TypeBlocPourcentage> calculerPourcentageBlocParEtat();

}
