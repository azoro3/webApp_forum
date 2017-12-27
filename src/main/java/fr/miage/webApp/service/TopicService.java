package fr.miage.webApp.service;

import fr.miage.webApp.model.Topic;
import fr.miage.webApp.repository.MessageRepository;
import fr.miage.webApp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic findById(String topicId){
        return topicRepository.findOne(topicId);
    }
}
