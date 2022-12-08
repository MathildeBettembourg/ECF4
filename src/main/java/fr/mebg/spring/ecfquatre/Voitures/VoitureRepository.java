package fr.mebg.spring.ecfquatre.Voitures;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoitureRepository extends MongoRepository<Voiture, String> {
}
