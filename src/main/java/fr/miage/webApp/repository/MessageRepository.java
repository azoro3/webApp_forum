package fr.miage.webApp.repository;

import fr.miage.webApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,String> {

}
