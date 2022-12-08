package fr.mebg.spring.ecfquatre.Voitures;

import fr.mebg.spring.ecfquatre.Locations.LocationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoitureConfigurations {
    @Bean
    VoitureService voitureService(VoitureRepository voitureRepository){
        return new VoitureServiceImpl(voitureRepository);
    }
}
