package fr.miage.webApp.repository;

import fr.miage.webApp.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,String> {

}
