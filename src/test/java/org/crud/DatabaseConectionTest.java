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
import java.sql.DatabaseMetaData;
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
public class DatabaseConectionTest{
    @Test 
    public void testDataBaseConection() throws SQLException, ClassNotFoundException{
        Connection connection = DatabaseConnection.getConnection();
        DatabaseMetaData metadata=connection.getMetaData();
        String url = metadata.getURL();
        assertEquals("jdbc:h2:./empleados",url);
    }
}
