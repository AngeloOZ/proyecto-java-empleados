/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import java.util.List;
import org.crud.empleados.domain.Puesto;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author alvar
 */

public class PuestoDAOGetPuestosTest {

    public PuestoDAOGetPuestosTest(){
    }
@Test
public void testGetPuestos() {
System.out.println("getPuestos");
PuestoDAO instance = new PuestoDAO();
boolean expResult = false;
List<Puesto> result = instance.getPuestos();
boolean x = result.isEmpty();
assertEquals(expResult, x);
}
}