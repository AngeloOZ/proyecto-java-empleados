/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angello Ordoñez
 */
public class EmpleadoDAODeleteTest {
    
    public EmpleadoDAODeleteTest() {
    }
    
    @Test
    public void testDeleteEmpleado() throws Exception {
        System.out.println("deleteEmpleado");
        EmpleadoDAO instance = new EmpleadoDAO();
        long id = instance.getLastId();
        boolean expResult = true;
        boolean result = instance.deleteEmpleado(id);
        assertEquals(expResult, result);
    }
    
}
