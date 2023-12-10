package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Etat;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeBlocPourcentage;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void deleteFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }


    public Set<TypeBlocPourcentage> calculerPourcentageBlocParEtat() {
        Long totalBlocsTotal = blocRepository.count();
        Set<TypeBlocPourcentage> resultSet = new HashSet<>();

        for (Etat etat : Etat.values()) {
            Float result = blocRepository.nbrBlocParEtat(etat);


            float pourcentage = (result != null ? result : 0.0f) * 100.0f / totalBlocsTotal;

            TypeBlocPourcentage BlocInfo = new TypeBlocPourcentage(etat, pourcentage);
            resultSet.add(BlocInfo);
        }

        return resultSet;
    }

}
