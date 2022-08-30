package zooApp.repository;

import zooApp.model.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository
{
    Zone save(Zone entity);
    List<Zone> findAll();

    Zone findHighestFoodUsageZone();
    Zone findLowestInhabitantsNumberZone();

    Optional<Zone> findById(Integer zoneId);
}
