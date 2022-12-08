package fr.mebg.spring.ecfquatre.Locations;

import fr.mebg.spring.ecfquatre.Voitures.VoitureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfigurations {
    @Bean
    LocationService locationService(LocationRepository locationRepository, VoitureService voitureService) {
        return new LocationServiceImpl(locationRepository, voitureService);
    }
}
