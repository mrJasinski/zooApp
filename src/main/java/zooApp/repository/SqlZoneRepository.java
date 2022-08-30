package zooApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zooApp.model.Zone;

@Repository
public interface SqlZoneRepository extends ZoneRepository, JpaRepository<Zone, Integer>
{
    @Override
    @Query(value = "SELECT * FROM zones WHERE zone_id = :zoneId", nativeQuery = true)
    Zone findHighestFoodUsageZone();

    @Override
    @Query(value = "SELECT * FROM zones WHERE zone_id = :zoneId", nativeQuery = true)
    Zone findLowestInhabitantsNumberZone();

}
