package fr.miage.webApp.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Topic {
    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "text")
    private String initialMessage;
    private String projectName;
    private String author;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<User> followingUsers = new LinkedList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(mappedBy = "topicId", cascade = {CascadeType.ALL})
    private List<Message> messages;

    public Topic() {
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setFollowingUsers(List<User> users) {
        this.followingUsers = users;
    }

    public List<User> getFollowingUsers() {
        return this.followingUsers;
    }
}
