package fr.mebg.spring.ecfquatre.Voitures;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

public interface VoitureRepository extends MongoRepository<Voiture, String> {

    List<Voiture>findByDisponibilite(Boolean disponibilite);
}
