package fr.mebg.spring.ecfquatre.Locataires;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locataires")
@CrossOrigin
public class LocataireController {
    private final LocatairesService locatairesService;


    public LocataireController(LocatairesService locatairesService) {
        this.locatairesService = locatairesService;
    }

    /**
     * Fonction FINALL(). Cette fonction permet de recuperer tous les locataires en bases de données.
     *
     * @return une liste de locataire.
     */
    @GetMapping("")
    public List<Locataire> findAll() {
        return locatairesService.findAll();
    }

    /**
     * Fonction Save() permet d'enregistrer un nouveau locataire en base de données.
     * Elle envoie une notification via le logger à chaque création.
     *
     * @param entity de type Locataire
     * @return l'entité qui vient d'être postée en base de donnée.
     */
    @PostMapping("")
    public Locataire save(@RequestBody Locataire entity) {
        return locatairesService.save(entity);
    }

    /**
     * Fonction findById est une fonction permet de rechercher et retourner un locatire qui est en base de donnée.
     * Si l'id utilisé est erroné ou non présent en BDD un logger de type warn est envoyé car cela peut être lié à
     * une attaque au vu du front qui est uniquement avec des select.
     * Une reponse Http est aussi renvoyée si l(id n'existe pas en BDD.
     *
     * @param id qui est l'Id de l'entité que l'on souhaite récuperer.
     * @return le Locataire trouvé en base de donnée
     */
    @GetMapping("{id}")
    public Locataire findById(@PathVariable String id) {
        return locatairesService.findById(id);
    }

    /**
     * Fonction deleteById permet de supprimer un utilisateur en base de données.
     * La suppresion étant peut courrantes / annonymisation on reçoit un logger de type warn à chaque deletion
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        locatairesService.deleteById(id);
    }
}
