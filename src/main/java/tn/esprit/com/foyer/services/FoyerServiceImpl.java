package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.repository.BlocRepository;
import tn.esprit.com.foyer.repository.FoyerRepository;
import tn.esprit.com.foyer.entity.Foyer;

import java.util.List;

@Service
@AllArgsConstructor
public  class FoyerServiceImpl implements FoyerService {
    FoyerRepository foyerRepository;
    BlocRepository blocRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).get();
    }

    @Override
    public void archiverFoyer(long idFoyer) {

    }
    @Override
    public Foyer addFoyerWithBloc (Foyer foyer){
        //sauvegarder le fils
        Foyer foyer1 = foyerRepository.save(foyer);
        //parcourir les lists des parents
        foyer.getBloc().stream().forEach(
                bloc -> {
                    bloc.setFoyer(foyer1);
                    blocRepository.save(bloc);
        }
        );
        return foyer;
        // !!!!  khtrna m child ll parent  !!!!
    }


}
