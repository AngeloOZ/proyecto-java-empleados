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
 * @author Angello Ordoñez
 */
public class EmpleadoDAOTest {
    
    public static long ID_EMPLEADO_TEST;
    
    public EmpleadoDAOTest() {
    }

    /**
     * Test of getEmpleados method, of class EmpleadoDAO.
     */
    @Test
    public void testGetEmpleados() throws Exception {
        System.out.println("getEmpleados");
        EmpleadoDAO instance = new EmpleadoDAO();
        List<Empleado> expResult = new ArrayList<>();
        List<Empleado> result = instance.getEmpleados();
        if(result.isEmpty()){
            fail("El array esta vacio");
        }
    }

    /**
     * Test of getEmpleado method, of class EmpleadoDAO.
     */
    @Test
    public void testGetEmpleado() throws Exception {
        System.out.println("getEmpleado");
        EmpleadoDAO instance = new EmpleadoDAO();
        long id = instance.getLastId();
        Empleado result = instance.getEmpleado(id);

        long resultID = result.getId();
        if(resultID != id){
            fail("error pero no se porque");
        }
    }

    /**
     * Test of saveEmpleado method, of class EmpleadoDAO.
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

    /**
     * Test of deleteEmpleado method, of class EmpleadoDAO.
     */
    @Test
    public void testDeleteEmpleado() throws Exception {
        System.out.println("deleteEmpleado");
        long id = ID_EMPLEADO_TEST;
        EmpleadoDAO instance = new EmpleadoDAO();
        boolean expResult = true;
        boolean result = instance.deleteEmpleado(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPuestoToEmpleado method, of class EmpleadoDAO.
     */
    @Test
    public void testSetPuestoToEmpleado() throws Exception {
        System.out.println("setPuestoToEmpleado");
        EmpleadoDAO instance = new EmpleadoDAO();
        long empleadoId = instance.getLastId();
        long puestoId = 1;
        instance.setPuestoToEmpleado(empleadoId, puestoId);
        Empleado empleado = new Empleado();
        empleado = instance.getEmpleado(empleadoId);
        
        assertNotNull("message", empleado.getPuesto());
    }
    
}
