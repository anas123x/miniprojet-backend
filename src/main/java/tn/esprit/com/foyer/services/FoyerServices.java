package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServices implements IFoyerService{
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
        Foyer f = foyerRepository.findById(idFoyer).get();
        f.setArchived(true);
        foyerRepository.save(f);
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

    @Override
    public void deleteFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}
