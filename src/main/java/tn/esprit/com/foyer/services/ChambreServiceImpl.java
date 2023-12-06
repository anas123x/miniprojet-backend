package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entity.TypeChambre;
import tn.esprit.com.foyer.entity.TypeChambrePourcentage;
import tn.esprit.com.foyer.repository.BlocRepository;
import tn.esprit.com.foyer.repository.ChambreRepository;
import tn.esprit.com.foyer.repository.ReservationRepository;
import tn.esprit.com.foyer.entity.Chambre;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements  ChambreService{
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    ReservationRepository reservationRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).get();
    }

    @Override
    public void removeChambre(Long idChambre) {
    }
    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        return chambreRepository.getChambresParNomBloc(nomBloc);
    }

    /*@Scheduled(fixedRate = 120000)
    public void pourcentageChambreParTypeChambre() {

        Long totalchbres = chambreRepository.count();
       for( TypeChambre i : TypeChambre.values() )
               {
                Float total= chambreRepository.nbrchambrepartype(i);
                        float pourcentage = (total/totalchbres)*100 ;

                        System.out.printf("nbre total des chbres "+totalchbres +" " +
                                          "Pourcentage de chambres de type %s: %.2f%%\n" , i , pourcentage );}

    }*/
    /*@Scheduled(fixedRate = 60000)
    public void nbPlacesDispo(){
        List<Chambre>ch =chambreRepository.findAll();
        int x=0;
        int currentYear= LocalDate.now().getYear();
        LocalDate instance =LocalDate.now().withYear(currentYear);
        LocalDate dateStart = instance.with(firstDayOfYear());
        LocalDate dateEnd = instance.with(lastDayOfYear());
        for (Chambre chambre : ch) {
            List<Reservation> availablePlaces = reservationRepository.findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(chambre.getNumeroChambre(),true,dateStart,dateEnd );
            int nbplace =availablePlaces.size();
            if(chambre.getTypeC() == TypeChambre.SIMPLE)
                x = 1;
            if(chambre.getTypeC() == TypeChambre.DOUBLE)
                x = 2;
            if(chambre.getTypeC() == TypeChambre.TRIPLE)
                x = 3;
            if((x - nbplace) > 0)
            {
                System.out.println("Chambre ID: " + chambre.getIdChambre() +
                        ", Places disponibles: " + (x - nbplace)  +
                        " pour l'année en cours."+ currentYear);
            }
            else
                System.out.println("nombre de place est negative");


            
        }
    }

     */
    /*@Override
    public String calculerPourcentageChambreParTypeChambre(boolean estValide) {
        Long totalChambres = chambreRepository.count();

        StringBuilder resultBuilder = new StringBuilder();

        for (TypeChambre typeChambre : TypeChambre.values()) {
            Float total = chambreRepository.nbrChambreParType(typeChambre, estValide);
            float pourcentage = (total / totalChambres) * 100;

            resultBuilder.append(String.format("Nombre total des chambres %d. Pourcentage de chambres de type %s %s: %.2f%%\n",
                    totalChambres, typeChambre, estValide ? "réservées" : "non réservées", pourcentage));
        }

        return resultBuilder.toString();
    }*/

    @Override
    public HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValide) {
        Long totalChambres = chambreRepository.count();
        HashSet<TypeChambrePourcentage> resultSet = new HashSet<>();

        for (TypeChambre typeChambre : TypeChambre.values()) {
            Float total = chambreRepository.nbrChambreParType(typeChambre, estValide);
            float pourcentage = (total / totalChambres) * 100;

            TypeChambrePourcentage chambreInfo = new TypeChambrePourcentage(typeChambre, pourcentage);
            resultSet.add(chambreInfo);
        }

        return resultSet;
    }



}



