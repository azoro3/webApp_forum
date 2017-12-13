package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Topic {
    @Id
    private String id;
    @OneToMany
    private Set<Message> messages;
}
