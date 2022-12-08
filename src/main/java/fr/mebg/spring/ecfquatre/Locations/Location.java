package fr.mebg.spring.ecfquatre.Locations;

import fr.mebg.spring.ecfquatre.Locataires.Locataire;
import fr.mebg.spring.ecfquatre.Utils.Entity;
import fr.mebg.spring.ecfquatre.Vehicules.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Location extends Entity {
//    @Id
//    private String id;

    @DBRef
    private Locataire locataire;

    @DBRef
    private Vehicule vehicule;

    private int prix;

    //concordance avec le front donc date de debut ici
    private LocalDate fullstart;

    private LocalDate fullend;

    @Transient//non stocké en BDD, donnée nécessaire pour le front uniquement
    public int getDuree(){
        int duree = (int)ChronoUnit.DAYS.between(fullstart, fullend)+1;
        return duree;
    }
}
