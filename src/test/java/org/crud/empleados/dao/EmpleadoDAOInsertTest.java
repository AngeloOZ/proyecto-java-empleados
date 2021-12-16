/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import org.crud.empleados.domain.Empleado;

/**
 *
 * @author Angello Ordoñez
 */
public class EmpleadoDAOInsertTest {
    
    public EmpleadoDAOInsertTest() {
    }
    
    @Test
    public void testSaveEmpleado() throws Exception {
        System.out.println("saveEmpleado");
        Empleado empleado = new Empleado();
        
        empleado.setNombre("pruebaTest1");
        empleado.setCorreo("prueba1@test.unit");
       
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = empleadoDAO.saveEmpleado(empleado);
                
        assertEquals(expResult, result);
    }
    
}
