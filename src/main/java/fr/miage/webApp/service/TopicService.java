package fr.miage.webApp.service;

import fr.miage.webApp.model.Topic;
import fr.miage.webApp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public void saveTopic(Topic topic) {
        topic.setId(UUID.randomUUID().toString());

        topicRepository.save(topic);
    }
}
