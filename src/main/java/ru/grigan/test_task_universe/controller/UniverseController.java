package ru.grigan.test_task_universe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.grigan.test_task_universe.model.Lord;
import ru.grigan.test_task_universe.model.Planet;
import ru.grigan.test_task_universe.service.UniverseService;

import java.util.List;

@Controller

public class UniverseController {
    private final UniverseService service;

    public UniverseController(UniverseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getLords(Model model) {
        List<Lord> lords = service.getAllLords();
        model.addAttribute("lords", lords);
        model.addAttribute("message", "All Lords from our universe");
        return "lord";
    }

    @GetMapping("/planets")
    public String getPlanets(Model model) {
        List<Planet> planets = service.getAllPlanets();
        model.addAttribute("planets", planets);
        return "planets";
    }

    @GetMapping("/addLord")
    public String getLordEditPage(Model model) {
        model.addAttribute("lord", new Lord());
        return "lordEditPage";
    }

    @GetMapping("/addPlanet")
    public String getPlanetEditPage(Model model) {
        model.addAttribute("planet", new Planet());
        return "planetEditPage";
    }

    @PostMapping("/saveLord")
    public String saveLord(@ModelAttribute Lord lord, Model model) {
        service.saveLord(lord);
        List<Lord> lords = service.getAllLords();
        model.addAttribute("lords", lords);
        model.addAttribute("message", "All Lords from our universe");
        return "lord";
    }

    @PostMapping("/savePlanet")
    public String savePlanet(@ModelAttribute Planet planet, Model model) {
        service.savePlanet(planet);
        List<Planet> planets = service.getAllPlanets();
        model.addAttribute("planets", planets);
        return "planets";
    }

    @GetMapping("/delete/{id}")
    public String deletePlanet(@PathVariable Integer id) {
        service.deletePlanetById(id);
        return "redirect:/planets";
    }

    @GetMapping("/selectPlanet/{lord}")
    public String selectPlanet(@PathVariable Lord lord, Model model) {
        model.addAttribute("lord", lord);
        model.addAttribute("planets", service.vacantPlanets());
        return "vacantPlanet";
    }

    @GetMapping("/setPlanet")
    public String setPlanet(@RequestParam(name = "planet") Integer planet,
                            @RequestParam(name = "lord") Integer lord) {
        Planet vacant = service.getPlanetById(planet);
        Lord master = service.getLordById(lord);
        vacant.setLord(master);
        service.savePlanet(vacant);
        return "redirect:/planets";
    }

    @GetMapping("/lazy")
    public String getLazyLord(Model model) {
        List<Lord> lords = service.getLazyLord();
        model.addAttribute("lords", lords);
        model.addAttribute("message", "Lazy Lords");
        return "lord";
    }

    @GetMapping("/young")
    public String getTenYoungestLord(Model model) {
        List<Lord> young = service.getTenYoungestLord();
        model.addAttribute("lords", young);
        model.addAttribute("message", "Top 10 youngest Lords");
        return "lord";

    }
}
