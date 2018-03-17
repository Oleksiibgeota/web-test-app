package com.test.mywebapp.dao.mysql;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class MySqlDBConnection {

    private DataSource dataSource;
    private static MySqlDBConnection instance;
    private static final AtomicLong COUNT = new AtomicLong(0);


    private MySqlDBConnection() {
        System.out.println("new connection =  " + COUNT.incrementAndGet());
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TestDB");
        } catch (NamingException e) {
            throw new IllegalStateException("jdbc/TestDB" + " is missing in JNDI!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static synchronized MySqlDBConnection getInstance() {
        if (instance == null) {
            instance = new MySqlDBConnection();
        }
        return instance;
    }
}
