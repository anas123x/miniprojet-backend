package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Etat;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeBlocPourcentage;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FoyerServices implements IFoyerService {
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


    @Override
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



