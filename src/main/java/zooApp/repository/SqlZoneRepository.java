package zooApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zooApp.model.Zone;

@Repository
public interface SqlZoneRepository extends ZoneRepository, JpaRepository<Zone, Integer>
{

}
