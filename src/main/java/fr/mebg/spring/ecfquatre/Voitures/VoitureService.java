package fr.mebg.spring.ecfquatre.Voitures;

import java.util.List;

public interface VoitureService {
    List<Voiture> findAll();

    Voiture save(Voiture entity);

    Voiture findById(String id);

    void deleteById(String id);

    Voiture modificationVoitureById(String id, Voiture entity);
}
