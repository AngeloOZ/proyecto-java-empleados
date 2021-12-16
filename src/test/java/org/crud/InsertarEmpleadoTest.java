/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import java.util.ArrayList;
import java.util.List;
import org.crud.empleados.domain.Empleado;
import org.crud.empleados.dao.EmpleadoDAO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angello Ordo�ez
 */
public class EmpleadoDAOTest {
    
    public static long ID_EMPLEADO_TEST;
    
    public InsertarEmpleadoTest() {
    }

    /**
     * Test of getEmpleados method, of class EmpleadoDAO.
     */
   
    @Test
    public void testSaveEmpleado() throws Exception {
        System.out.println("saveEmpleado");
        Empleado empleado = new Empleado();
        
        empleado.setNombre("pruebaTest1");
        empleado.setCorreo("prueba1@test.unit");
       
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = empleadoDAO.saveEmpleado(empleado);
        
        ID_EMPLEADO_TEST = empleadoDAO.getLastId();
        System.out.println("Id de insercsion: " + ID_EMPLEADO_TEST);               
        assertEquals(expResult, result);
    }
    
}
