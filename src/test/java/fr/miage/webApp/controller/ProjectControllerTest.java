package fr.miage.webApp.controller;

import fr.miage.webApp.WebAppForumApplication;
import fr.miage.webApp.model.Project;
import fr.miage.webApp.model.Topic;
import fr.miage.webApp.repository.ProjectRepository;
import fr.miage.webApp.repository.TopicRepository;
import fr.miage.webApp.validator.ProjectValidator;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebAppForumApplication.class})
public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TopicRepository topicRepository;

    @MockBean
    private ProjectValidator projectValidator;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");


        this.mockMvc = MockMvcBuilders.standaloneSetup(projectController)
                .setViewResolvers(viewResolver)
                .build();

        projectRepository.deleteAll();
    }

    @Test
    public void shouldHave1ProjectSavedWhenSendingForm() throws Exception {
        this.mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("subject", "Android")
                .param("author", "Florian"))
                .andExpect(status().is3xxRedirection());

        Assert.assertEquals(1, this.projectRepository.findAll().size());
    }

    @Test
    public void showProjectGridTest() throws Exception {
        Project project = new Project();
        project.setAuthor("Arthur");
        project.setSubject("Cordova");
        project.setTopics(new HashSet<>());

        Project project2 = new Project();
        project2.setAuthor("Florian");
        project2.setSubject("Android");
        project2.setTopics(new HashSet<>());

        this.projectRepository.save(project);
        this.projectRepository.save(project2);

        Assert.assertEquals(2, this.projectRepository.findAll().size());

        this.mockMvc.perform(get("/projects"))
                .andExpect(view().name("welcome"))
                .andExpect(model().attributeExists("projects"))
                .andExpect(model().attribute("projects", Matchers.hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    public void showTopicGridTest() throws Exception {
        Project project = new Project();
        project.setAuthor("Florian");
        project.setSubject("Android");
        project.setTopics(new HashSet<>());

        Topic topic = new Topic();
        topic.setId(UUID.randomUUID().toString());
        topic.setCreationDate(new Date());
        topic.setInitialMessage("test message");
        topic.setAuthor("Florian");
        topic.setTitle("Retrofit");
        topic.setProjectName("Android");

        this.projectRepository.save(project);
        this.topicRepository.save(topic);

        Assert.assertEquals(1, this.projectRepository.findAll().size());

        this.mockMvc.perform(get("/projects/"+project.getSubject()))
                .andExpect(view().name("topicGrid"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attributeExists("topics"))
                .andExpect(model().attribute("project", hasProperty("subject", is("Android"))))
                //.andExpect(model().attribute("project", hasProperty("topics", hasSize(1))))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRedirectIfProjectNameDoesntExist() throws Exception {
        this.mockMvc.perform(get("/projects/"+"randomeProject"))
                .andExpect(status().is3xxRedirection());
    }
}
