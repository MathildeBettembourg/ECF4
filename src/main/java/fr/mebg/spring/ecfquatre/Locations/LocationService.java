package fr.mebg.spring.ecfquatre.Locations;

import java.util.List;

public interface LocationService {
    List<Location> findAll();

    Location save(Location entity);

    Location findById(String id);

    void deleteById(String id);

    Location miseAjourLocation(String id, Location entity);

}
