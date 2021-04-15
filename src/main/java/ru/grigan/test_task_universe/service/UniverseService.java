package ru.grigan.test_task_universe.service;

import org.springframework.stereotype.Service;
import ru.grigan.test_task_universe.model.Lord;
import ru.grigan.test_task_universe.model.Planet;
import ru.grigan.test_task_universe.repository.LordRepository;
import ru.grigan.test_task_universe.repository.PlanetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UniverseService {
    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;

    public UniverseService(LordRepository lordRepository, PlanetRepository planetRepository) {
        this.lordRepository = lordRepository;
        this.planetRepository = planetRepository;
    }


    public List<Lord> getAllLords() {
        List<Lord> lords = new ArrayList<>();
        lordRepository.findAll().forEach(lords::add);
        return lords;
    }

    public void saveLord(Lord lord) {
        lordRepository.save(lord);
    }

    public void savePlanet(Planet planet) {
        planetRepository.save(planet);
    }

    public List<Planet> getAllPlanets() {
        List<Planet> planets = new ArrayList<>();
        planetRepository.findAll().forEach(planets::add);
        return planets;
    }

    public void deletePlanetById(Integer id) {
        planetRepository.deleteById(id);
    }

    public List<Planet> vacantPlanets() {
        return planetRepository.findPlanetsByLordIdIsNull();
    }

    public Planet getPlanetById(Integer planet) {
        return planetRepository.findById(planet).orElseThrow(
                () -> new NoSuchElementException("Planet with id = " + planet + " not found")
        );
    }

    public Lord getLordById(Integer lord) {
        return lordRepository.findById(lord).orElseThrow(
                () -> new NoSuchElementException("Lord with id = " + lord + " not found")
        );
    }

    public List<Lord> getLazyLord() {
        return lordRepository.findLordByPlanetsIsNull();
    }

    public List<Lord> getTenYoungestLord() {
        return lordRepository.youngLord().stream().limit(10).collect(Collectors.toList());
    }
}
