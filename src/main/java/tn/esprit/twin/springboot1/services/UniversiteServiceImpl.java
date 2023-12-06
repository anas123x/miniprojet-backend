package tn.esprit.twin.springboot1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin.springboot1.entity.Foyer;
import tn.esprit.twin.springboot1.entity.Universite;
import tn.esprit.twin.springboot1.repository.FoyerRepository;
import tn.esprit.twin.springboot1.repository.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService {
    UniversiteRepository universiteRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversity(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversity(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversity(long idUniversity) {
        return universiteRepository.findById(idUniversity).get();
    }

    @Override
    public void removeUniversity(long idUniversity) {
        universiteRepository.deleteById(idUniversity);

    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        //trouver le parent
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        //trouver le fils et l'avoir
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        //affecter le fils au parent (setfils)
        universite.setFoyer(foyer);
        //enregistrer le parent
        universiteRepository.save(universite);
        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        //trouver le parent par l id passé en parametre (orelse ou bien get)
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite != null) {
            // Dissocier le foyer de l'université
            universite.setFoyer(null); //REMINDER:relation onetoone
            universiteRepository.save(universite);
        }
        return universite;
    }
}
