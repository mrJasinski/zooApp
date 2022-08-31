package zooApp.service;

import org.springframework.stereotype.Service;
import zooApp.model.Animal;
import zooApp.model.Zone;
import zooApp.repository.AnimalRepository;
import zooApp.repository.ZoneRepository;

import java.util.*;

@Service
public class ZooService
{
    private final AnimalRepository animalRepository;
    private final ZoneRepository zoneRepository;

    public ZooService(AnimalRepository animalRepository, ZoneRepository zoneRepository) {
        this.animalRepository = animalRepository;
        this.zoneRepository = zoneRepository;
    }

    public List<Animal> readAllAnimals()
    {
        return this.animalRepository.findAll();
    }

    public Animal createAnimal(Animal toCreate)
    {
        return this.animalRepository.save(toCreate);
    }

    public Zone createZone(Zone toCreate) {
        return this.zoneRepository.save(toCreate);
    }

    public List<Zone> readAllZones()
    {
        return this.zoneRepository.findAll();
    }

    public List<Animal> readAnimalsByZoneId(Integer zoneId)
    {
       return this.animalRepository.findAnimalsByZoneId(zoneId);
    }

    public List<Animal> readAnimalsByName(String name)
    {
       return this.animalRepository.findAnimalsByName(name);
    }

    public String readZoneWithHighestFoodUsage()
    {
        var map = new HashMap<Zone, Integer>();

        for (Zone zone : readAllZones())
        {
            int usage = 0;

            for (Animal animal : zone.getAnimals())
                usage += animal.getType().getFoodUsage();

            map.put(zone, usage);
        }

        return String.format("Strefa z największym zapotrzebowaniem na karmę to: %s Zapotrzebowanie wynosi: %s jednostek karmy dziennie",
                Collections.max(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey().getName(),
                Collections.max(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getValue());
    }

    public String readZoneWithLowestInhabitantsNumber()
    {
        var map = new HashMap<Zone, Integer>();

        for (Zone zone : readAllZones())
        {
            map.put(zone, zone.getAnimals().size());
        }

        return String.format("Strefa z najmniejszą ilością zwierząt to: %s Liczba przypisanych zwierząt to: %s",
                Collections.min(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey().getName(),
                Collections.min(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getValue());
    }
}
