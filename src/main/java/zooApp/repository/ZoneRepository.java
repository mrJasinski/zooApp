package zooApp.repository;

import zooApp.model.Zone;

import java.util.List;

public interface ZoneRepository
{
    Zone save(Zone entity);
    List<Zone> findAll();
}
