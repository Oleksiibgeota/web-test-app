package com.test.mywebapp.dao;

import com.test.mywebapp.domain.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserByName(String name);

    void createUser(String firstName, String lastName);

    void updateUser(int id, String firstName, String lastName);

    void deleteUserByFirstNameAndLastName(String firstName, String lastName);
}
