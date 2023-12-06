package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.entity.Bloc;
import tn.esprit.twin.springboot1.entity.TypeChambre;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface BlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc b);

    Bloc updateBloc(Bloc b);

    Bloc retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
    Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc) ;
    long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);
    public void  listeChambresParBloc();
    byte[] generatePdfForBloc(Bloc bloc) throws IOException;
    //Map<String, Map<TypeChambre, Double>> statistiquesParBlocEtAnnee();
    List<Map<String, Object>> obtenirInfosBlocsParFoyer();

}
