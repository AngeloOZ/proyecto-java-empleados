package org.crud.empleados.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    private static final String DATABASE_CHECK_TABLES = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES "
            + "where table_type = 'TABLE'";

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream in = new FileInputStream("database.properties")) {
            properties.load(in);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void initDatabase() throws ClassNotFoundException, SQLException, IOException {
        int tables = 0;
        try (Connection connection = getConnection()) {
            final Statement stmt = connection.createStatement();
            final String sql = DATABASE_CHECK_TABLES;
            final ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tables++;
            }
            rs.close();
        } finally {
            if (tables == 0) {
                this.createTables("database_init.sql");
            }
        }
    }

    private void createTables(final String fileName) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connection = getConnection()) {
            final Statement stmt = connection.createStatement();
            final byte[] sqlFileData = Files.readAllBytes(Paths.get(fileName));
            final String fileData = new String(sqlFileData);
            final String[] statements = fileData.split(";");
            if (statements != null && statements.length > 0) {
                for (String statement : statements) {
                    statement = statement.trim();
                    stmt.execute(statement);
                }
            }
        } finally {
            logger.info("tables created");
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        final String url = properties.getProperty("database.url");
        final String userName = properties.getProperty("database.username");
        final String password = properties.getProperty("database.password");
        final Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

}
