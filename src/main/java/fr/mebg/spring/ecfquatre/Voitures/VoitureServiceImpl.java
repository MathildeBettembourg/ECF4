package fr.mebg.spring.ecfquatre.Voitures;

import fr.mebg.spring.ecfquatre.Locataires.Locataire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
     * Fonction existe by id pour verifier si la voiture existe. Utilisée pour véréfier le status de la voiture
     * @param id
     * @return un boolean
     */
    public boolean existsById(String id) {
        return voitureRepository.existsById(id);
    }
}
