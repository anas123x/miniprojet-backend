package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.TypeChambre;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc b);

    Bloc updateBloc(Bloc b);

    Bloc retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
    Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc) ;
    long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);
    public void  listeChambresParBloc();
    byte[] generatePdfForBloc(Bloc bloc) throws IOException;
    List<Map<String, Object>> obtenirInfosBlocsParFoyer();
}
