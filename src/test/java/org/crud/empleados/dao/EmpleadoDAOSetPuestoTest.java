/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.crud.empleados.domain.Empleado;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angello Ordo�ez
 */
public class EmpleadoDAOSetPuestoTest {

    public EmpleadoDAOSetPuestoTest() {
    }

    @Test
    public void testSetPuestoToEmpleado() throws Exception {
        EmpleadoDAO instance = new EmpleadoDAO();
        long empleadoId = instance.getLastId();
        long puestoId = 1;
        instance.setPuestoToEmpleado(empleadoId, puestoId);
        Empleado empleado = new Empleado();
        empleado = instance.getEmpleado(empleadoId);

        assertNotNull("message", empleado.getPuesto());
    }
}
