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

    /**
     * Method to save a topic in database
     *
     * @param topic topic to save
     */
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    /**
     * Method to find a topic by his id
     *
     * @param topicId Id of the topic to find
     * @return Topic
     */
    public Topic findById(String topicId) {
        return topicRepository.findOne(topicId);
    }
}
