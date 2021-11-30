/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.crud;

import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.crud.empleados.dao.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;


/**
 *
 * @author chimb
 */
public class DatabaseConectionTest {
     private static Properties properties;
         private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
     static {
        properties = new Properties();
        try (InputStream in = new FileInputStream("database.properties")) {
            properties.load(in);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    @Test
    
    public void test() throws SQLException {
        final String url = properties.getProperty("database.url");
        final String userName = properties.getProperty("database.username");
        final String password = properties.getProperty("database.password");
        final Connection connection = DriverManager.getConnection(url, userName, password);
    }
}
