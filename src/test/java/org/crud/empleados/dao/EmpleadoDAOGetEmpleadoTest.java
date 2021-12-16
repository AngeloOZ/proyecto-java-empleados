/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import java.util.ArrayList;
import java.util.List;
import org.crud.empleados.domain.Empleado;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angello Ordo�ez
 */
public class EmpleadoDAOGetEmpleadoTest {

    public EmpleadoDAOGetEmpleadoTest() {
    }

    @Test
    public void testGetEmpleado() throws Exception {
        EmpleadoDAO instance = new EmpleadoDAO();
        long id = instance.getLastId();
        Empleado result = instance.getEmpleado(id);

        long resultID = result.getId();
        if (resultID != id) {
            fail("error pero no se porque");
        }
    }

    @Test
    public void testGetEmpleados() throws Exception {
        System.out.println("getEmpleados");
        EmpleadoDAO instance = new EmpleadoDAO();
        List<Empleado> expResult = new ArrayList<>();
        List<Empleado> result = instance.getEmpleados();
        if (result.isEmpty()) {
            fail("El array esta vacio");
        }
    }

}
