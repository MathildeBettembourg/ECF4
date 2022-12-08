package fr.mebg.spring.ecfquatre.Locataires;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Locataire {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String email;
    private String telephone;
}
