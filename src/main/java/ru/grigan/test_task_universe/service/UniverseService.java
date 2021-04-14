package ru.grigan.test_task_universe.service;

import org.springframework.stereotype.Service;
import ru.grigan.test_task_universe.model.Lord;
import ru.grigan.test_task_universe.repository.LordRepository;
import ru.grigan.test_task_universe.repository.PlanetRepository;

import java.util.ArrayList;
import java.util.List;

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
}
