package fr.miage.webApp.controller;

import fr.miage.webApp.model.Message;
import fr.miage.webApp.model.Project;
import fr.miage.webApp.model.Topic;
import fr.miage.webApp.model.User;
import fr.miage.webApp.service.MailSender;
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
    @Autowired
    private MailSender mailSender;

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
            topic.getFollowingUsers().add(u);
            topicService.saveTopic(topic);
            mailSender.send(u.getEmail(),"Merci d'avoir créer le topic "+title,"création Topic");

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

    @RequestMapping(value = "/projects/{projectSubject}/{topicId}", method = RequestMethod.POST)
    public String postMessage(Model model,
                              @PathVariable("projectSubject") String projectName,
                              @PathVariable("topicId") String topicId,
                              @RequestParam("message") String messageContent,
                              @RequestParam("author") String authorName) {
        Topic topic = topicService.findById(topicId);
        if (topic != null) {
            Message message = new Message();
            message.setId(UUID.randomUUID().toString());
            message.setContent(messageContent);
            message.setAuthor(authorName);
            message.setCreationDate(new Date());
            message.setTopicId(topic.getId());

            topic.getMessages().add(message);
            topicService.saveTopic(topic);

            List<User> followingUsers = topic.getFollowingUsers();
            String mailContent = "Nouveau message dans le topic %s : \n %s";
            for(User follower : followingUsers){
                System.out.println("follower = " + follower.getEmail());
                mailSender.send(follower.getEmail(),String.format(mailContent, topic.getTitle(), messageContent),"Nouveau message");
            }

            model.addAttribute("topic", topic);
            model.addAttribute("messages", topic.getMessages());
            return "messageGrid";
        } else {
            return "redirect:/projects/" + projectName;
        }
    }

    @RequestMapping(value = "/projects/{projectSubject}/{topicId}/subscribe", method = RequestMethod.POST)
    public String subscribe(Model model, @PathVariable("topicId") String topic) {
        String u = securityService.findLoggedInUsername();
        System.out.println("TopicController.subscribe : "+u);
        User currentUser = userService.findByUsername(u);
        Topic currentTopic = topicService.findById(topic);
        model.addAttribute("topic", currentTopic);
        System.out.println("currentUser : "+currentUser.getUsername());
        if (currentTopic.getFollowingUsers().contains(currentUser)) {
            System.out.println("already subscribed");
        } else {
            currentTopic.getFollowingUsers().add(currentUser);
            topicService.saveTopic(currentTopic);
            String content = "Bienvenue sur le topic " + currentTopic.getTitle();
            String subject = "abonnement à un topic";
            //TODO faire l'envoi du mail
            mailSender.send("fakeadresstoto@yopmail.com", content, subject);
        }
        return "redirect:/projects/" + topic;
    }
}
