package com.test.mywebapp.dao.mysql;

import com.test.mywebapp.dao.UserDao;
import com.test.mywebapp.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO implements UserDao {

    private MySqlDBConnection dbConnection = new MySqlDBConnection("jdbc/TestDB");

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setNameFirst(rs.getString("name"));
                user.setNameLast(rs.getString("family"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB users size: " + users.size());
        return users;
    }

    public User getUserByName(String name) {
        User user = new User();
        String query = "SELECT * FROM user WHERE name = ?;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setNameFirst(rs.getString("name"));
                user.setNameLast(rs.getString("family"));
                user.setSalary(rs.getInt("salary"));
            }
            System.out.println("DB users size: " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        String query = "INSERT INTO user (name, family, salary) VALUES (?,?,?);";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            int salary = (int) (Math.random() * 2000);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, salary);
            if (firstName.isEmpty() && lastName.isEmpty()) {
                System.out.println("error , first name or last name is empty");
            } else {
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String firstName, String lastName) {
        String query = "UPDATE user SET name = ?, family = ? WHERE id = ?;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserByFirstNameAndLastName(String firstName, String lastName) {
        String query = "DELETE FROM user WHERE name = ? and family = ?;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}