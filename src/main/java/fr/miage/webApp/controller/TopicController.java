package fr.miage.webApp.controller;

import fr.miage.webApp.model.Message;
import fr.miage.webApp.model.Project;
import fr.miage.webApp.model.Topic;
import fr.miage.webApp.model.User;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.service.SecurityService;
import fr.miage.webApp.service.TopicService;
import fr.miage.webApp.service.UserService;
import fr.miage.webApp.validator.TopicValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//TODO afficher les messages d'erreur
@Controller
public class TopicController {

    @Autowired
    private TopicValidator topicValidator;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/createTopic", method = RequestMethod.GET)
    public String createTopic(Model model) {
        model.addAttribute("createTopicForm", new Topic());
        return "createTopic";

    }

    @RequestMapping(value = "/projects/{projectSubject}", method = RequestMethod.POST)
    public String createTopic(Model model, @PathVariable("projectSubject") String subject,
            @RequestParam("title") String title,
            @RequestParam("message") String messageContent,
            @RequestParam("author") String author,
            @RequestParam("projectName") String projectName) {
        Project project = projectService.findBySubject(projectName);
        if (project != null) {
            model.addAttribute("project", project);
            model.addAttribute("topics", project.getTopics());
            System.out.println("Create topic : " + title + " | " + messageContent + " | " + author + " | " + projectName);

            Topic topic = new Topic();
            topic.setId(UUID.randomUUID().toString());
            topic.setAuthor(author);
            topic.setProjectName(projectName);
            topic.setTitle(title);
            topic.setInitialMessage(messageContent);
            topic.setCreationDate(new Date());
            User u = userService.findByUsername(author);
            System.out.println(topic.getAuthor());
            System.out.println(u);
            topic.getFollowingUsers().add(u);
            topicService.saveTopic(topic);

            return "topicGrid";
        } else {
            return "redirect:/projects";
        }
    }

    @RequestMapping(value = "/projects/{projectSubject}/{topicId}", method = RequestMethod.GET)
    public String getTopic(Model model, @PathVariable("projectSubject") String projectName,
            @PathVariable("topicId") String topicId) {
        Topic topic = topicService.findById(topicId);
        if (topic != null) {
            model.addAttribute("topic", topic);
            model.addAttribute("messages", topic.getMessages());
            return "messageGrid";
        } else {
            return "redirect:/projects/" + projectName;
        }
    }

    @RequestMapping(value = "/projects/{projectSubject}/{topicId}/subscribe", method = RequestMethod.POST)
    public String subscribe(Model model, @PathVariable("topicId") String topic) {
        User currentUser = userService.findByUsername(securityService.findLoggedInUsername());
        Topic currentTopic = topicService.findById(topic);
        model.addAttribute("topic",currentTopic);
        currentTopic.getFollowingUsers().add(currentUser);
        return "redirect:/projects/"+topic;
    }
}
