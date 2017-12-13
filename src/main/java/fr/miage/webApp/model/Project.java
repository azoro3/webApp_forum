package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Project {
    @Id
    private String id;
    private Set<Topic> topics;
}
