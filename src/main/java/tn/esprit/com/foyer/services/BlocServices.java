package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.ChambreRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class BlocServices implements IBlocService{
    BlocRepository blocRepository;
    FoyerRepository foyerRepository;
    ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }


    @Override
    public byte[] generatePdfForBloc(Bloc bloc) throws IOException {
        // Génère le PDF en utilisant la méthode auxiliaire
        return generatePdf(bloc);
    }

    public byte[] generatePdf(Bloc bloc) throws IOException {
        // Crée un nouveau document PDF
        PDDocument document = new PDDocument();

        // Ajoute une nouvelle page au document
        PDPage page = new PDPage();
        document.addPage(page);

        // Utilise un flux de contenu pour ajouter du texte à la page
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Titre du PDF
            addText(contentStream, "Chambres du Bloc : " + bloc.getNomBloc(), 20, 700, PDType1Font.HELVETICA_BOLD, 12);

            // Liste des chambres
            List<Chambre> chambres = List.copyOf(bloc.getChambre());

            float currentYOffset = 580; // Ajustez la valeur initiale selon vos besoins

            for (Chambre chambre : chambres) {
                contentStream.beginText();
                contentStream.newLineAtOffset(20, currentYOffset);

                // Concatène le numéro de la chambre et son statut dans la même ligne
                String chambreInfo = "Chambre numéro : " + chambre.getNumeroChambre() +
                        " - Statut : " + (chambre.getReservations() != null && !chambre.getReservations().isEmpty() ? "Réservée" : "Non Réservée");

                contentStream.showText(chambreInfo);
                contentStream.newLineAtOffset(0, -20);
                contentStream.endText();

                currentYOffset -= 40; // Ajustez la valeur en fonction de l'espacement souhaité entre les chambres
            }
        }

        // Convertit le document en tableau d'octets
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        // Retourne le tableau d'octets représentant le fichier PDF
        return byteArrayOutputStream.toByteArray();
    }

    private void addText(PDPageContentStream contentStream, String text, float xOffset, float yOffset, PDType1Font font, int fontSize) throws IOException {
        contentStream.beginText();
        if (font != null) {
            contentStream.setFont(font, fontSize);
        }
        contentStream.newLineAtOffset(xOffset, yOffset);
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -20);
        contentStream.endText();
    }


    @Override
    public List<Map<String, Object>> obtenirInfosBlocsParFoyer() {
        List<Map<String, Object>> foyersInfos = new ArrayList<>();

        List<Foyer> foyers = foyerRepository.findAll();
        for (Foyer foyer : foyers) {
            Map<String, Object> foyerInfo = new HashMap<>();
            foyerInfo.put("nomFoyer", foyer.getNomFoyer());

            List<Map<String, Object>> blocsInfos = new ArrayList<>();
            List<Bloc> blocs = blocRepository.findByFoyer(foyer);

            for (Bloc bloc : blocs) {
                Map<String, Object> blocInfo = new HashMap<>();
                blocInfo.put("nomBloc", bloc.getNomBloc());

                List<Map<String, Object>> chambresInfos = new ArrayList<>();
                List<Chambre> chambres = chambreRepository.findByBloc(bloc);

                for (Chambre chambre : chambres) {
                    int capaciteChambre = determinerCapaciteChambre(chambre.getTypeC());
                    Long capaciteBloc = chambreRepository.obtenirCapaciteBlocParChambre(chambre);
                    int placesNonReservees = capaciteChambre - chambre.getReservations().size();

                    Map<String, Object> chambreInfo = new HashMap<>();
                    chambreInfo.put("numeroChambre", chambre.getNumeroChambre());
                    chambreInfo.put("capaciteMax", capaciteChambre);
                    chambreInfo.put("placesNonReservees", placesNonReservees);

                    chambresInfos.add(chambreInfo);
                }

                blocInfo.put("chambres", chambresInfos);
                blocsInfos.add(blocInfo);
            }

            foyerInfo.put("blocs", blocsInfos);
            foyersInfos.add(foyerInfo);
        }

        return foyersInfos;
    }

    private int determinerCapaciteChambre(TypeChambre typeChambre) {
        switch (typeChambre) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0;
        }
    }


    /*@Scheduled(fixedRate = 10000)

    public void performTask() {
        log.info("start");
        List<Bloc> blocs = blocRepository.findAll();
        log.info("size : "+blocs.size());
       for (int i=0;i<blocs.size();i++)
        {
            log.info("La capacité du bloc "+blocs.get(i).getNomBloc()+ "est : " + blocs.get(i).getCapaciteBloc());
           Set<Chambre> chambres = blocs.get(i).getChambres();
            chambres.stream().forEach(
                    chambre -> {
                        log.info("le numero de la chambre est : "+ chambre.getNumeroChambre() + " et de type : " + chambre.getTypeC());
                    }
            );
        }

    }*/
    @Override
    public List<Bloc> findByFoyer(Foyer foyer) {
        return blocRepository.findByFoyer(foyer);
    }
}