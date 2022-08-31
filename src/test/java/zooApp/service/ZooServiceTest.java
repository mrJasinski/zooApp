package zooApp.service;

import org.junit.jupiter.api.Test;
import zooApp.model.Animal;
import zooApp.model.Type;
import zooApp.model.Zone;
import zooApp.repository.AnimalRepository;
import zooApp.repository.ZoneRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ZooServiceTest
{
    @Test
    void readAllZones_shouldReadAllZones()
    {
//        given
        var mockZoneRepo = mock(ZoneRepository.class);

        given(mockZoneRepo.findAll()).willReturn(prepareZonesWithAnimals());

//        system under test
        var toTest = new ZooService(null, mockZoneRepo);

//        when
        var result = toTest.readAllZones();

//        then
        assertThat(result, hasSize(3));
    }

    @Test
    void readAllAnimals_shouldReadAllAnimals()
    {
//        given
        var mockAnimalRepo = mock(AnimalRepository.class);

        given(mockAnimalRepo.findAll()).willReturn(prepareAnimalsWithZones());

//        system under test
        var toTest = new ZooService(mockAnimalRepo, null);

//        when
        var result = toTest.readAllAnimals();

//        then
        assertThat(result, hasSize(6));
    }

    @Test
    void readZoneWithHighestFoodUsage_shouldReturnReportWithHighestFoodUsageZoneData()
    {
//        given
        var mockAnimalRepo = mock(AnimalRepository.class);
        var mockZoneRepo = mock(ZoneRepository.class);

//        system under test
        var toTest = new ZooService(mockAnimalRepo, mockZoneRepo);

        given(toTest.readAllZones()).willReturn(prepareZonesWithAnimals());

//        when
        var result = toTest.readZoneWithHighestFoodUsage();

//        then
        assertEquals(result, "Strefa z największym zapotrzebowaniem na karmę to: Wybieg trawiasty Zapotrzebowanie wynosi: 35 jednostek karmy dziennie");
    }

    @Test
    void readZoneWithHighestFoodUsage_shouldReturnReportWithLowestInhabitantsNumberZoneData()
    {
//        given
        var mockAnimalRepo = mock(AnimalRepository.class);
        var mockZoneRepo = mock(ZoneRepository.class);

//        system under test
        var toTest = new ZooService(mockAnimalRepo, mockZoneRepo);

        given(toTest.readAllZones()).willReturn(prepareZonesWithAnimals());

//        when
        var result = toTest.readZoneWithLowestInhabitantsNumber();

//        then
        assertEquals(result, "Strefa z najmniejszą ilością zwierząt to: Wybieg piaszczysty Liczba przypisanych zwierząt to: 1");
    }

    @Test
    void readAnimalsByZoneId_shouldReturnListOfAnimalsInGivenZone()
    {
//        given
        var mockAnimalRepo = mock(AnimalRepository.class);

        var zoneId = 1;

        given(mockAnimalRepo.findAnimalsByZoneId(zoneId)).willReturn(prepareAnimalsByZoneId(zoneId));

//        system under test
        var toTest = new ZooService(mockAnimalRepo, null);

//        when
        var result = toTest.readAnimalsByZoneId(zoneId);

//        then
        assertThat(result, hasSize(1));
    }

    @Test
    void readAnimalsByName_shouldReturnAnimalsWithGivenName()
    {
        //        given
        var mockAnimalRepo = mock(AnimalRepository.class);

        var name = "Nemo";

        given(mockAnimalRepo.findAnimalsByName(name)).willReturn(prepareAnimalsByName(name));

//        system under test
        var toTest = new ZooService(mockAnimalRepo, null);

//        when
        var result = toTest.readAnimalsByName(name);

//        then
        assertThat(result, hasSize(3));
    }

    private List<Animal> prepareAnimals()
    {
        var animal1 = new Animal();
        var animal2 = new Animal();
        var animal3 = new Animal();
        var animal4 = new Animal();
        var animal5 = new Animal();
        var animal6 = new Animal();

        animal1.setName("Nemo");
        animal2.setName("Oskar");
        animal3.setName("Nemo");
        animal4.setName("BugsBunny");
        animal5.setName("Nemo");
        animal6.setName("LolaBunny");

        animal1.setType(Type.ELEPHANT);
        animal2.setType(Type.ELEPHANT);
        animal3.setType(Type.LION);
        animal4.setType(Type.ELEPHANT);
        animal5.setType(Type.LION);
        animal6.setType(Type.RABBIT);

        return Arrays.asList(animal1, animal2, animal3, animal4, animal5, animal6);
    }

    private List<Animal> prepareAnimalsWithZones()
    {
        var animals = prepareAnimals();
        var zones = prepareZones();

        animals.get(0).setZone(zones.get(0));
        animals.get(1).setZone(zones.get(1));
        animals.get(2).setZone(zones.get(1));
        animals.get(3).setZone(zones.get(2));
        animals.get(4).setZone(zones.get(2));
        animals.get(5).setZone(zones.get(2));

        return animals;
    }

    private List<Animal> prepareAnimalsByZoneId(int zoneId)
    {
        var result = new ArrayList<Animal>();

        for (Animal a : prepareAnimalsWithZones())
            if (a.getZone().getId() == zoneId)
                result.add(a);

        return result;
    }

    private List<Animal> prepareAnimalsByName(String name)
    {
        var result = new ArrayList<Animal>();

        for (Animal a : prepareAnimals())
            if ((a.getName()).equals(name))
                result.add(a);

        return result;
    }

    private List<Zone> prepareZones()
    {
        var zone1 = new Zone();
        var zone2 = new Zone();
        var zone3 = new Zone();

        zone1.setId(1);
        zone2.setId(2);
        zone3.setId(3);

        zone1.setName("Wybieg piaszczysty");
        zone2.setName("Wybieg zadrzewiony");
        zone3.setName("Wybieg trawiasty");

        return Arrays.asList(zone1, zone2, zone3);
    }

    private List<Zone> prepareZonesWithAnimals()
    {
        var animals = prepareAnimals();
        var zones = prepareZones();

        zones.get(0).setAnimals(new HashSet<>(List.of(animals.get(0))));
        zones.get(1).setAnimals(new HashSet<>(List.of(animals.get(1), animals.get(2))));
        zones.get(2).setAnimals(new HashSet<>(List.of(animals.get(3), animals.get(4), animals.get(5))));

        return zones;
    }
}