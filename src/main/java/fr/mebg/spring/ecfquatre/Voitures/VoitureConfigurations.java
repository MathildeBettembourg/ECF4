package fr.mebg.spring.ecfquatre.Voitures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoitureConfigurations {
    @Bean
    VoitureService voitureService(VoitureRepository voitureRepository){
        return new VoitureServiceImpl(voitureRepository);
    }
}
