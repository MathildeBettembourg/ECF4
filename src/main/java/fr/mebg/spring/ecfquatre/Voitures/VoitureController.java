package fr.mebg.spring.ecfquatre.Voitures;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/voitures")
@CrossOrigin
public class VoitureController {
    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    /**
     * Fonction find All permet de recuperer toutes les voitures en base de donnée.
     *
     * @return une liste de voiture.
     */
    @GetMapping("")
    public List<Voiture> findAll() {
        return voitureService.findAll();
    }

    /**
     * Fonction save permet d'enregistrer une nouvelle voiture en base de donnée,
     * un logger notifie de la creation de la voiture de type info en donnant le type et l'id.
     *
     * @param entity de type voiture
     * @return la nouvelle voiture sauvegardée en base de données.
     */
    @PostMapping("")
    public Voiture save(@RequestBody Voiture entity) {
        return voitureService.save(entity);
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
    @GetMapping("{id}")
    public Voiture findById(@PathVariable String id) {
        return voitureService.findById(id);
    }

    /**
     * Fonction deleteById permet de supprimer une voiture en base de données.
     * La suppresion étant peut courrantes on reçoit un logger de type warn à chaque deletion de voiture
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        voitureService.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'une voiture
     * L'id du path est recupéré,
     * une fois la concordance vérifiée on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité en mettant a jours celle en BDD.
     * * @param id     du path, venant du useParams de REACT
     *
     * @param id
     * @param entity voiture à modifier
     * @return la voiture qui à été modifiée
     */
    @PutMapping("{id}")
    public Voiture modificationVoitureById(@PathVariable String id,
                                           @RequestBody Voiture entity) {
        if (!Objects.equals(id, entity.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "id de l'objet et du path non concordants");
        } else {
            return this.voitureService.modificationVoitureById(id, entity);
        }
    }

}
