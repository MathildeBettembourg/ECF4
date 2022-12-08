package fr.mebg.spring.ecfquatre.Voitures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {
    @Id
    private String id;
    private String modele;
    private String etat;
    private int prix;
    private Boolean disponibilite;
    private String marque;
}
