package fr.miage.webApp.controller;

import fr.miage.webApp.model.Topic;
import fr.miage.webApp.service.TopicService;
import fr.miage.webApp.validator.TopicValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//TODO afficher les messages d'erreur
@Controller
public class TopicController {
    @Autowired
    private TopicValidator topicValidator;
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/createTopic", method = RequestMethod.GET)
    public String createTopic(Model model) {
        model.addAttribute("createTopicForm", new Topic());
        return "createTopic";

    }

    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public String createTopic(@ModelAttribute("createTopicForm") Topic createTopicForm, BindingResult bindingResult, Model model) {
        topicValidator.validate(createTopicForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createTopic";
        }
        topicService.saveTopic(createTopicForm);
        return "redirect:/welcome";
    }
}
