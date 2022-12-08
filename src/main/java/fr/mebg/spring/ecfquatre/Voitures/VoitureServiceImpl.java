package fr.mebg.spring.ecfquatre.Voitures;

import fr.mebg.spring.ecfquatre.Locataires.LocatairesService;
import fr.mebg.spring.ecfquatre.Locations.Location;
import fr.mebg.spring.ecfquatre.Locations.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VoitureServiceImpl implements VoitureService {
    private final VoitureRepository voitureRepository;

    private static final Logger logger = LoggerFactory.getLogger(VoitureServiceImpl.class);

    public VoitureServiceImpl(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;

    }

    /**
     * Fonction find All permet de recuperer toutes les voitures en base de donnée.
     *
     * @return une liste de voiture.
     */
    @Override
    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    /**
     * Fonction save permet d'enregistrer une nouvelle voiture en base de donnée,
     * un logger notifie de la creation de la voiture de type info en donnant le type et l'id.
     *
     * @param entity de type voiture
     * @return la nouvelle voiture sauvegardée en base de données.
     */
    @Override
    public Voiture save(Voiture entity) {
        logger.info("Creation en base de donne de l'entite VOITURE : " + entity.getId());
        entity.setDateModification(LocalDateTime.now());
        return voitureRepository.save(entity);
    }

    /**
     * FindById est une fonction qui permet de recuperer une voiture en BDD grâce à son id;
     * Si l'id utilisé est erroné ou non présent en BDD un logger de type warn est envoyé car cela peut être lié à
     * une attaque au vu du front qui est uniquement avec des select.
     * Une reponse Http est aussi renvoyée si l(id n'existe pas en BDD.
     *
     * @param id venant du path correspondant à l'element à rechercher en BDD.
     * @return la voiture trouvée en BDD
     */
    @Override
    public Voiture findById(String id) {
        return voitureRepository.findById(id).orElseThrow(() -> {
            logger.warn("tentative de recuperation d'une entite VOITURE avec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Fonction deleteById permet de supprimer une voiture en base de données.
     * La suppresion étant peut courrantes on reçoit un logger de type warn à chaque deletion de voiture
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        logger.warn("deletion de la VOITURE " + id);
        voitureRepository.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'une voiture
     * L'id du path est recupéré,
     * une fois la concordance vérifiée on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité en mettant a jours celle en BDD.
     * * @param id     du path, venant du useParams de REACT
     *
     * @param entity voiture à modifier
     * @return la voiture qui à été modifiée
     */
    @Override
    public Voiture modificationVoitureById(String id, Voiture entity) {
        Voiture voitureAmodifier = this.findById(id);
        if (voitureAmodifier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            voitureAmodifier.setEtat(entity.getEtat());
            voitureAmodifier.setPrix(entity.getPrix());
            voitureAmodifier.setModele(entity.getModele());
            voitureAmodifier.setMarque(entity.getMarque());
            voitureAmodifier.setDisponibilite(entity.getDisponibilite());
        }
        return this.save(voitureAmodifier);

    }

    /**
     * Fonction existe by id pour verifier si la voiture existe. Utilisée pour vérifier le status de la voiture
     *
     * @param id
     * @return un boolean
     */
    public boolean existsById(String id) {
        return voitureRepository.existsById(id);
    }

    /**
     * Fonction pour modifier via l'url l'etat de la voiture
     *
     * @param id   de la voiture
     * @param etat a changer
     * @return la voiture modifiée
     */
    @Override
    public Voiture modificationEtatVoiture(String id, String etat) {
        Voiture voitureAModifier = this.findById(id);
        if (Objects.equals(voitureAModifier.getEtat(), etat)) {
            return voitureAModifier;
        }
        voitureAModifier.setEtat(etat);
        return this.save(voitureAModifier);
    }

    @Override
    public List<Voiture> recupererVoitureFonctionsEtat(String etat) {
        List<Voiture> listeVoitureEtat = new ArrayList<>();

        return null;
    }
/*****************************************************************************************************************************************
    /**
     * Fonction pour créer une liste de location de la voiture, permet de gérer les voitures
     *
     * @param id  de la voiture
     * @param idl de la location
     * @return
     */
//    @Override
//    public Boolean ajoutDeLocationDansDocumentVoiture(String id, String idl) {
//        Voiture voitureOuDoitAjouterLocation = this.findById(id);
//        //liste de location ou doit ajouter location
//        List<Location> listeLocationsDeVoiture = voitureOuDoitAjouterLocation.getLocataireActuelEtAVenir();
//        Location locationAAjouterDansLaListe = this.locationService.findById(idl);
//        boolean flag = false;
//        if (listeLocationsDeVoiture.size() == 0) {
//            listeLocationsDeVoiture.add(locationAAjouterDansLaListe);
//        } else {
//            for (Location locationDeListe : listeLocationsDeVoiture
//            ) {
//                if ((Objects.equals(locationDeListe.getId(), voitureOuDoitAjouterLocation.getId()))
//                        || (locationDeListe.getFullend().isAfter(locationAAjouterDansLaListe.getFullstart()))
//                        || ((locationAAjouterDansLaListe.getFullend().isAfter(locationDeListe.getFullstart())
//                ))) {
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                listeLocationsDeVoiture.add(locationAAjouterDansLaListe);
//                return flag;
//            }
//        }
//        return flag;
//    }
}
