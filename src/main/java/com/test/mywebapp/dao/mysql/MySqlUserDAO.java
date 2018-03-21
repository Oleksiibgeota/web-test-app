package com.test.mywebapp.dao.mysql;

import com.test.mywebapp.dao.UserDao;
import com.test.mywebapp.domain.Car;
import com.test.mywebapp.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlUserDAO implements UserDao {
    private MySqlDBConnection dbConnection = MySqlDBConnection.getInstance();

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id;";
        Map<Integer, User> usersMap = new HashMap<>();
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt(1);
                if (usersMap.containsKey(userId)) {
                    User user = usersMap.get(userId);
                    Car car = new Car();
                    rs.getInt("car_id");
                    car.setId(rs.getInt("car_id"));
                    car.setName(rs.getString(10));
                    user.getCars().add(car);
                    usersMap.put(userId, user);
                } else {
                    User user = new User();
                    user.setId(userId);
                    user.setNameFirst(rs.getString(2));
                    user.setNameLast(rs.getString("family"));
                    Car car = new Car();
                    List<Car> cars = new ArrayList<>();
                    rs.getInt("car_id");
                    car.setId(rs.getInt("car_id"));
                    car.setName(rs.getString(10));
                    cars.add(car);
                    user.setCars(cars);
                    usersMap.put(userId, user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(new ArrayList<User>(usersMap.values()));
        return new ArrayList<User>(usersMap.values());
    }

    public User getUserByName(String name) {
        String query = "SELECT * FROM user WHERE name = ?;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNameFirst(rs.getString("name"));
                user.setNameLast(rs.getString("family"));
                user.setSalary(rs.getInt("salary"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getListCarFromUser(int userId) {
        String query = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id WHERE user.id=?;";
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, (userId));
            ResultSet rs = stmt.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                Car car = new Car();
                car.setName(rs.getString(10));
                cars.add(car);
                System.out.println(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id;";
        User user = new User();
        List<Car> cars = new ArrayList<>();
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                System.out.println("count before = " + count);
                int getId = rs.getInt(1);
                if (getId == id && count > 0) {
                } else if (getId == id && count == 0) {
                    Car car = new Car();
                    user.setId(id);
                    user.setNameFirst(rs.getString(2));
                    user.setNameLast(rs.getString("family"));
                    car.setId(rs.getInt("car_id"));
                    car.setName(rs.getString(10));
                    cars.add(car);
                    user.setCars(cars);
                    System.out.println("count in loop one = " + count++);
                }
            }

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