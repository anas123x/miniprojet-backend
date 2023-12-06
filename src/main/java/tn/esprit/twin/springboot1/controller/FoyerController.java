package tn.esprit.twin.springboot1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.springboot1.entity.Foyer;
import tn.esprit.twin.springboot1.services.FoyerService;
import tn.esprit.twin.springboot1.services.FoyerServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/foyer")
public class FoyerController {
    FoyerService foyerService;
    @GetMapping("retrieveAllFoyers")
    List<Foyer> retrieveAllFoyers(){
        List<Foyer>foyerList=foyerService.retrieveAllFoyers();
        return foyerList;
    }
    @PostMapping("addFoyer")
    public Foyer addFoyer(@RequestBody Foyer f){
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    @PutMapping("updateFoyer")
    public Foyer updateFoyer(@RequestBody Foyer f){
        Foyer foyer =  foyerService.updateFoyer(f);
        return foyer;
    }
    @GetMapping("/retrieveFoyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable ("foyer-id") Long idFoyer){
        return  foyerService.retrieveFoyer(idFoyer);
    }

    @PostMapping("/addfoyerbloc")
    public Foyer ajouterfoyeravecbloc (@RequestBody Foyer f){
        Foyer foyer = foyerService.addFoyerWithBloc(f);
        return foyer;
    }
}