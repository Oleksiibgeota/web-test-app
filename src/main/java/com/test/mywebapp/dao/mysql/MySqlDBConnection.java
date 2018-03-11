package com.test.mywebapp.dao.mysql;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class MySqlDBConnection {

    private DataSource dataSource;

    private static final AtomicLong COUNT = new AtomicLong(0);

    public MySqlDBConnection(final String jndiname) {
        System.out.println("new connection =  " + COUNT.incrementAndGet());
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
        } catch (NamingException e) {
            throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
