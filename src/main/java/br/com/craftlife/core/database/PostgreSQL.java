package br.com.craftlife.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class PostgreSQL extends Database{

    private final String username;
    private final String password;
    private final String connectionURL;

    /**
     * Creates a new MySQL instance for a specific database
     *
     * @param connectionURL
     *            Database url connection
     * @param username
     *            Username
     * @param password
     *            Password
     */
    public PostgreSQL(String connectionURL,String username, String password) {
        this.connectionURL = connectionURL;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection(connectionURL, username, password);

        return connection;
    }

}

