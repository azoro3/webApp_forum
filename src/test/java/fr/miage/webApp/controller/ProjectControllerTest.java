package fr.miage.webApp.controller;

import fr.miage.webApp.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class ProjectControllerTest {
    private MockMvc mvc;

    @Test
    public void shoudlReturn401IfNotAutenticated() throws Exception {
        this.mvc.perform(get("/projects"))
                .andExpect(status().isUnauthorized());
    }
}
