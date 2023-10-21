package com.dictionaryapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private Long id;

    private String username;

    private boolean isLogged;


    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void login(String username, Long id) {
        this.username = username;
        this.isLogged = true;
        this.id = id;
    }

    public void logout() {
        this.username = null;
        this.isLogged = false;
    }
}


