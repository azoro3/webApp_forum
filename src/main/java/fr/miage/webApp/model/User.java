package fr.miage.webApp.model;

import java.util.List;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_entity")
public class User {

    @ManyToMany(mappedBy = "followingUsers")
    private List<Topic> topics;
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany
    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setTopics(List<Topic> topics){
        this.topics=topics;
    }
    public List<Topic> getTopics(){
        return this.topics;
    }
}
