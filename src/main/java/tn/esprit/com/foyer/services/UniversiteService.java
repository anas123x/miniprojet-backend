package tn.esprit.com.foyer.services;

import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entity.Universite;

import java.util.List;
@Service

public interface UniversiteService {
    List<Universite> retrieveAllUniversities();
    Universite addUniversity (Universite u);
    Universite updateUniversity (Universite u);
    Universite retrieveUniversity (long idUniversity);
    void removeUniversity  (long idUniversity );
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) ;
    Universite desaffecterFoyerAUniversite (long idUniversite);
}
