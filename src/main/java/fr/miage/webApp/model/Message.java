package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    private String id;
    private User author;
    private String content;
}
