package ru.grigan.test_task_universe.repository;

import org.springframework.data.repository.CrudRepository;
import ru.grigan.test_task_universe.model.Planet;

import java.util.List;

public interface PlanetRepository extends CrudRepository<Planet, Integer> {
    List<Planet> findPlanetsByLordIdIsNull();
}
