package fr.mebg.spring.ecfquatre.Voitures;

import fr.mebg.spring.ecfquatre.Locataires.Locataire;
import fr.mebg.spring.ecfquatre.Locations.Location;
import fr.mebg.spring.ecfquatre.Utils.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Voiture extends Entity {
//    @Id
//    private String id;
    private String modele;
    private String etat;
    private int prix;
    private Boolean disponibilite;
    private String marque;

}
