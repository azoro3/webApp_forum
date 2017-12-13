package fr.miage.webApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_entity")
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
}
