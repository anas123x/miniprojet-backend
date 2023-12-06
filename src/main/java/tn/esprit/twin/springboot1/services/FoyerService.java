package tn.esprit.twin.springboot1.services;

import tn.esprit.twin.springboot1.entity.Foyer;

import java.util.List;

public interface FoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer (Foyer f);
    Foyer updateFoyer (Foyer f);
    Foyer retrieveFoyer (long idFoyer);
    void archiverFoyer (long idFoyer);
    Foyer addFoyerWithBloc (Foyer foyer);
}
