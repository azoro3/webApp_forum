package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message {
    @Id
    private String id;
    @OneToOne
    private User author;
    private String content;
}
