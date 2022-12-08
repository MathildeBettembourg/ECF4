package fr.mebg.spring.ecfquatre.Voitures;

import fr.mebg.spring.ecfquatre.Locations.Location;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VoitureService {
    List<Voiture> findAll();

    Voiture save(Voiture entity);

    Voiture findById(String id);

    void deleteById(String id);

    Voiture modificationVoitureById(String id, Voiture entity);
     boolean existsById(String id);
     Voiture modificationEtatVoiture(String id, String etat);
     List<Voiture>recupererVoitureFonctionsEtat(@RequestParam String etat);
//    Boolean ajoutDeLocationDansDocumentVoiture(String id, String idl);
}
