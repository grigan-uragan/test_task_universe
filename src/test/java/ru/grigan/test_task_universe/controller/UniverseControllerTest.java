package ru.grigan.test_task_universe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create_universe_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_universe_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UniverseControllerTest {
    @Autowired
    private UniverseController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldControllerNotNull() {
        assertNotNull(controller);
    }

    @Test
    public void whenGoToMainPageThenSeeAllLordsFromOurUniverse() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("All Lords from our universe")));

    }

    @Test
    public void whenGoToMainPageThenPageContains3Lords() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='lord']").nodeCount(3));
    }

    @Test
    public void whenGoToPlanetsPageThenPageContains3Planets() throws Exception {
        this.mockMvc.perform(get("/planets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='planet']").nodeCount(3));
    }

    @Test
    public void whenGoToLazyLordPageThenPageContainsOneLordWithNameThird() throws Exception {
        this.mockMvc.perform(get("/lazy"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='lord']").nodeCount(1))
                .andExpect(xpath("//div[@id='lord']").string(containsString("third")));
    }

    @Test
    public void whenSaveNewPlanetThenPlanetsPageContains4Planets() throws Exception {
        this.mockMvc.perform(post("/savePlanet").param("name", "new planet"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='planet']").nodeCount(4));
    }

    @Test
    public void whenSaveNewLordThenLordsPageContains4Lords() throws Exception {
        this.mockMvc.perform(post("/saveLord")
                .param("name", "new lord").param("age", "99"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='lord']").nodeCount(4));
    }

    @Test
    public void whenDeletePlanetThenRedirectOnThePlanetsPage() throws Exception {
        this.mockMvc.perform(get("/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/planets"));
    }



}