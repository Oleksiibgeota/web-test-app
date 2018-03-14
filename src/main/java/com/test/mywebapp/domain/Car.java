package com.test.mywebapp.domain;

import java.util.List;

public class Car {
    private int id;
    private String name;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        String car = name == null ? "not car" : String.valueOf(name);
        return car;
    }
}
