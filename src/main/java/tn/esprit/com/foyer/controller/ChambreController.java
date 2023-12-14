package tn.esprit.com.foyer.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.TypeChambrePourcentage;
import tn.esprit.com.foyer.services.BlocServices;
import tn.esprit.com.foyer.services.ChambreServices;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ChambreController {
    ChambreServices chambreServices;
    BlocServices blocServices;
    @GetMapping("/chambre/get-all-chambres")
    public List<Chambre> retrieveChambres(){
        return chambreServices.retrieveAllChambre();
    }
    @GetMapping("/admin/chambre/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId){
        return chambreServices.retrieveChambre(chambreId);
    }
    @PostMapping("/admin/chambre/add-chambre")
    public Chambre addChambre(@RequestBody Chambre ch){
        return chambreServices.addChambre(ch);
    }

    @DeleteMapping("/admin/chambre/delete-chambre/{chambre-id}")
    public void deleteChambre(@PathVariable("chambre-id") Long chambreId){
        chambreServices.removeChambre(chambreId);
    }
    @PutMapping("/admin/chambre/affecterListeChambre/{nomBloc}")
    public void  affecterChambreBloc(@PathVariable("nomBloc") String nomBloc,@RequestBody List<Long> ch){
        chambreServices.affecterChambresABloc(ch,nomBloc);

    }
    @GetMapping("/admin/chambre/calculerPourcentageChambre")
    public HashSet<TypeChambrePourcentage> calculerPourcentageChambre1(@RequestParam boolean estValide) {
        return chambreServices.calculerPourcentageChambreParTypeChambre1(estValide);
    }

}