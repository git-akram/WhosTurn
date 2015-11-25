package com.whoseturn.api.rest.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private Long id;

    private String username;

    public User(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
    }

    public User(Long id, String username) {
        this.setId(id);
        this.setUsername(username);
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}