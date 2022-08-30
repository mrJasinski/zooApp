package zooApp.repository;

import zooApp.model.Animal;

import java.util.List;

public interface AnimalRepository
{
    Animal save(Animal entity);
    List<Animal> findAll();
    List<Animal> findAnimalsByZoneId(Integer zoneId);
    List<Animal> findAnimalsByName(String name);
//
//    Integer sumFoodUsageByZoneId(Integer zoneId);
//    Integer sumInhabitantsNumberByZoneId(Integer zoneId);
}
