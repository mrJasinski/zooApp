package zooApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zooApp.model.Animal;
import zooApp.model.Zone;
import zooApp.service.ZooService;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ZooController
{
    private final ZooService zooService;

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }

    @GetMapping(path = "/animals")
    ResponseEntity<?> readAllAnimals()
    {
        return ResponseEntity.ok(this.zooService.readAllAnimals());
    }

    @GetMapping(path = "/zones")
    ResponseEntity<?> readAllZones()
    {
        return ResponseEntity.ok(this.zooService.readAllZones());
    }

    @PostMapping(path = "/animals")
    ResponseEntity<Animal> createAnimal(@RequestBody @Valid Animal toCreate)
    {
        var result = this.zooService.createAnimal(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping(path = "/zones")
    ResponseEntity<Zone> createZone(@RequestBody @Valid Zone toCreate)
    {
        var result = this.zooService.createZone(toCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(path = "animalsInZone/{zoneId}")
    ResponseEntity<?> readAnimalsByZone(@PathVariable("zoneId") Integer zoneId)
    {
        return ResponseEntity.ok(this.zooService.readAnimalsByZoneId(zoneId));
    }

    @GetMapping(path = "animals/{name}")
    ResponseEntity<?> readAnimalsByName(@PathVariable("name") String name)
    {
        return ResponseEntity.ok(this.zooService.readAnimalsByName(name));
    }

    @GetMapping(path = "/highestFoodUsageZone")
    ResponseEntity<?> readZoneWithHighestFoodUsage()
    {
        return ResponseEntity.ok(this.zooService.readZoneWithHighestFoodUsage());
    }

    @GetMapping(path = "/lowestInhabitantsNumberZone")
    ResponseEntity<?> readZoneWithLowestInhabitantsNumber()
    {
        return ResponseEntity.ok(this.zooService.readZoneWithLowestInhabitantsNumber());
    }
}
