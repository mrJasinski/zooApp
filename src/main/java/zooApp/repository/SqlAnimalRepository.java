package zooApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zooApp.model.Animal;

import java.util.List;

@Repository
public interface SqlAnimalRepository extends AnimalRepository, JpaRepository<Animal, Integer>
{
    @Override
    @Query(value = "SELECT * FROM animals WHERE zone_id = :zoneId", nativeQuery = true)
    List<Animal> findAnimalsByZoneId(Integer zoneId);

    @Override
    @Query(value = "SELECT * FROM animals WHERE name = :name", nativeQuery = true)
    List<Animal> findAnimalsByName(String name);
}
