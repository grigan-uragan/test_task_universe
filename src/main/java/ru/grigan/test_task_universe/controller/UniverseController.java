package ru.grigan.test_task_universe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.grigan.test_task_universe.model.Lord;
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
        return "lord";
    }
}
