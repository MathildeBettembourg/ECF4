package fr.mebg.spring.ecfquatre.Locations;

import fr.mebg.spring.ecfquatre.Voitures.Voiture;
import fr.mebg.spring.ecfquatre.Voitures.VoitureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final VoitureService voitureService;
    private static final Logger logger= LoggerFactory.getLogger(LocationServiceImpl.class);

    public LocationServiceImpl(LocationRepository locationRepository, VoitureService voitureService) {
        this.locationRepository = locationRepository;
        this.voitureService = voitureService;
    }

    /**
     * Fonction findAll() permet de renvoyer toutes les locations en base de donnée (BDD).
     * @return une liste de locations.
     */
    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    /**
     * Fonction save permet de sauver une nouvelle location en base de BDD;
     * Lors de cette action la disponibilité de la voiture passe de disponible à non disponible.
     * un logger avertit de cet enregistrement
     * @param entity de type location
     * @return la nouvelle location enregistrée dans la BDD;
     */
    @Override
    public Location save(Location entity) {
        Voiture voitureALouer = this.voitureService.findById(entity.getVoiture().getId());
        logger.info("voiture " + voitureALouer.getDisponibilite());
        //modification du status de disponibilité de la voiture
        voitureALouer.setDisponibilite(false);
        logger.info("voiture " + voitureALouer.getDisponibilite());
        if (!this.voitureService.existsById(voitureALouer.getId())) {
            logger.info("not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Entite LOCATION sauvee en base de donnée " + voitureALouer.getModele());
            this.voitureService.modificationVoitureById(voitureALouer.getId(), voitureALouer);
        }
        entity.setDateModification(LocalDateTime.now());
        return locationRepository.save(entity);
    }

    /**
     * Fonction findById est une fonction permet de rechercher et retourner une location qui est en BDD.
     * Si l'id utilisé est erroné ou bien non présent en BDD, un logger de type "warn" est envoyé.
     * Une reponse Http NOT_FOUND avec un message est aussi renvoyée si l'id n'existe pas en BDD.
     * @param id qui est l'Id de l'entité que l'on souhaite récuperer.
     * @return la location trouvée en BDD
     */
    @Override
    public Location findById(String id) {
        return locationRepository.findById(id).orElseThrow(()->{
            logger.warn("tentative de recuperation d'une entite LOCATION avec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "location non trouvée en BD");
        });
    }

    /**
     * Fonction DeleteById() permet de supprimer une location - impossible depuis mon front.
     * s'i 'il y avait une version "Manager" de localib elle aurait cette fonctionnalitéé.
     * @param id de la location à supprimer
     */
    @Override
    public void deleteById(String id) {
        locationRepository.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'une location.
     * L'id du path est recupéré,
     * Une fois la concordance vérifiée entre le path et l'objet on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité.
     * @param id  du path, venant du useParams de REACT, id de la location à modifier
     * @param entity location modifiée
     * @return la ocation qui a été modifiée
     */
    @Override
    public Location miseAjourLocation(String id, Location entity) {
        Location locationAmodifier = this.findById(id);
        if (locationAmodifier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            locationAmodifier.setPrix(entity.getPrix());
            locationAmodifier.setVoiture(entity.getVoiture());
            locationAmodifier.setLocataire(entity.getLocataire());
            locationAmodifier.setFullstart(entity.getFullstart());
            locationAmodifier.setFullend(entity.getFullend());
        }
        return this.save(locationAmodifier);
    }
}
