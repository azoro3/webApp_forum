package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Project {
    @Id
    private String id;
    @OneToMany
    private Set<Topic> topics;
}
