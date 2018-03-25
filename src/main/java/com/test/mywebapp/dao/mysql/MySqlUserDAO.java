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
    private static final String QUERY_GET_ALL_USERS_WITH_CAR = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id;";
    private static final String QUERY_GET_USER_BY_ID_WITH_CAR = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id WHERE user.id = ?;";
    private static final String QUERY_GET_USER_BY_ID = "SELECT * FROM user WHERE name = ?;";
    private static final String QUERY_GET_LIST_CAR_FROM_USER = "SELECT * FROM user LEFT JOIN user_car ON user.id = user_car.user_id LEFT JOIN car ON user_car.car_id = car.id WHERE user.id=?;";
    private static final String QUERY_CREATE_CAR = "INSERT INTO user (name, family, salary) VALUES (?,?,?);";
    private static final String QUERY_UPDATE_USER = "UPDATE user SET name = ?, family = ? WHERE id = ?;";
    private static final String QUERY_DELETE_USER_BY_FIRST_NAME_AND_LAST_NAME = "DELETE FROM user WHERE name = ? and family = ?;";
    private static final String QUERY_DELETE_CAR_FROM_USER_BY_USER_ID = "DELETE FROM user_car WHERE user_id = ? and car_id = ?";
    private static final String QUERY_GET_CAR_ID_BY_NAME_CAR = "SELECT * FROM car WHERE name = ?;";

    private User populateUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setNameFirst(rs.getString(2));
        user.setNameLast(rs.getString("family"));
        return user;
    }

    private Car populateCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setName(rs.getString(10));
        return car;
    }

    public List<User> getAllUsers() {
        Map<Integer, User> usersMap = new HashMap<>();
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_GET_ALL_USERS_WITH_CAR)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt(1);
                if (usersMap.containsKey(userId)) {
                    User user = usersMap.get(userId);
                    Car car = populateCar(rs);
                    user.getCars().add(car);
                } else {
                    User user = populateUser(rs);
                    user.setCars(new ArrayList<>());
                    if (rs.getInt("car_id") != 0) {
                        Car car = populateCar(rs);
                        user.getCars().add(car);
                    }
                    usersMap.put(userId, user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(usersMap.values());
        return new ArrayList<>(usersMap.values());
    }

    public User getUserByName(String name) {
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_GET_USER_BY_ID)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return populateUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getListCarFromUser(int userId) {
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_GET_LIST_CAR_FROM_USER)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                Car car = populateCar(rs);
                cars.add(car);
                System.out.println(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCarIdByCarName(String carName) {
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_GET_CAR_ID_BY_NAME_CAR)) {
            stmt.setString(1, carName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(null);
    }

    public void createCarForUser(int userId, String carName) {
//        create method createCarFor User
    }

    public void deleteCarByUserIdAndCarName(int userId, String carName) {
        int carId = getCarIdByCarName(carName);
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_DELETE_CAR_FROM_USER_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, carId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getUserById(int id) {
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_GET_USER_BY_ID_WITH_CAR)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.wasNull()) {
                User user = new User();
                user.setCars(new ArrayList<>());
                while (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setNameFirst(rs.getString(2));
                    user.setNameLast(rs.getString("family"));
                    Car car = populateCar(rs);
                    user.getCars().add(car);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_CREATE_CAR)) {
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
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_UPDATE_USER)) {
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
        try (Connection con = dbConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY_DELETE_USER_BY_FIRST_NAME_AND_LAST_NAME)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
