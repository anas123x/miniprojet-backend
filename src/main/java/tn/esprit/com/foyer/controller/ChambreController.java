package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entity.Chambre;
import tn.esprit.com.foyer.entity.TypeChambrePourcentage;
import tn.esprit.com.foyer.services.ChambreService;

import java.util.HashSet;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
@CrossOrigin(origins = "http://localhost:4200")
public class ChambreController {
    ChambreService chambreService;
    @GetMapping("/getallchambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambre = chambreService.retrieveAllChambres();
        return listChambre;
    }

    @GetMapping("/getchambre/{chambreid}")
    public Chambre retrieveChambre(@PathVariable("universite-id") Long IdChambre) {
        return chambreService.retrieveChambre(IdChambre);
    }


    @PostMapping("/addchambre")
    public Chambre addChambre(@RequestBody Chambre ch) {
        Chambre chambre = chambreService.addChambre(ch);
        return chambre;
    }

    @DeleteMapping("/removechambre/{chambreid}")
    public void removeChambre(@PathVariable("chambreid") Long IdChambre) {
        chambreService.removeChambre(IdChambre);
    }

    @PutMapping("/updatechambre")
    public Chambre updateChambre(@RequestBody Chambre ch) {
        Chambre chambre = chambreService.updateChambre(ch);
        return chambre;
    }
    @GetMapping("/getchparbloc/{nomBloc}")
    public List<Chambre> getParNomBloc(@PathVariable ("getchparbloc") String nomBloc){
        return chambreService.getChambresParNomBloc(nomBloc);
    }

    /*@GetMapping("/pourcentage-par-type")
    public ResponseEntity<String> calculerPourcentageChambreParTypeChambre(@RequestParam(name = "estValide", defaultValue = "true") boolean estValide) {
        String result = chambreService.calculerPourcentageChambreParTypeChambre(estValide);
        return ResponseEntity.ok(result);
    }*/

    @GetMapping("/calculerPourcentageChambre")
    public HashSet<TypeChambrePourcentage> calculerPourcentageChambre1(@RequestParam boolean estValide) {
        return chambreService.calculerPourcentageChambreParTypeChambre1(estValide);
    }


}
