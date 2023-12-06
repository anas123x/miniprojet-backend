package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.entity.Bloc;
import tn.esprit.com.foyer.entity.TypeChambre;
import tn.esprit.com.foyer.services.BlocService;
import tn.esprit.com.foyer.services.ChambreService;
import tn.esprit.com.foyer.services.FoyerService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/bloc")
@CrossOrigin(origins = "http://localhost:4200")
public class BlocController {
    BlocService blocService;
    ChambreService chambreService;
    FoyerService foyerService;

    @GetMapping("/getallblocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }
    @GetMapping("/getbloc/{blocID}")
    public Bloc retrieveBloc(@PathVariable("blocID") Long blocId) {

        return blocService.retrieveBloc(blocId);
    }
    @PostMapping("/addbloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc  bloc = blocService.addBloc(b);
        return bloc;
    }
    @DeleteMapping("/deletebloc/{blocID}")
    public void removeBloc(@PathVariable("blocID") Long blocId) {

        blocService.removeBloc(blocId);
    }
    @PutMapping("/updatebloc")
    public Bloc updateBloc(@RequestBody Bloc b ){
        Bloc bloc= blocService.updateBloc(b);
        return bloc;
    }
    @PutMapping("/affecter/{nombloc}")
    public Bloc affecter(@RequestBody List<Long> numChambre,@PathVariable("nombloc")  String nomBloc) {
        Bloc bloc = blocService.affecterChambresABloc( numChambre,  nomBloc);
        return bloc;
    }

    @GetMapping("nbrchambre/{idBloc}/{type}")
    public long nbChParTypeEtBloc(@Param("type") TypeChambre type, @Param("idBloc") long idBloc){
        return blocService.nbChambreParTypeEtBloc(type , idBloc);
    }
    @GetMapping("{id}/generatepdf")
    public ResponseEntity<byte[]> generateChambrePdf(@PathVariable Long id) throws IOException {
        Bloc bloc = blocService.retrieveBloc(id);// ... remplacez par votre logique d'obtention d'utilisateur
        // Générez le PDF
        byte[] pdfBytes = blocService.generatePdfForBloc(bloc);
        HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_PDF); headers.setContentDispositionFormData("inline", "bloc.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK); }


    @GetMapping("/infos-blocs")
    public ResponseEntity<List<Map<String, Object>>> obtenirInfosBlocsParFoyer() {
        List<Map<String, Object>> blocsInfos = blocService.obtenirInfosBlocsParFoyer();
        return new ResponseEntity<>(blocsInfos, HttpStatus.OK);
    }

}
