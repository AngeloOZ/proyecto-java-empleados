/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.crud;


import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import org.crud.empleados.dao.DatabaseConnection;
import java.sql.SQLException;
/**
 *
 * @author chimb
 */
public class CrearConexion {
        @Test 
        public void testDataBaseConection() throws SQLException, ClassNotFoundException{
        Connection connection = DatabaseConnection.getConnection();
        DatabaseMetaData metadata=connection.getMetaData();
        String url = metadata.getURL();
        assertEquals("jdbc:h2:./empleados",url);
    }
    
    
}
