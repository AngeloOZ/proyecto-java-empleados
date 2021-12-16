/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.crud.empleados.domain.Puesto;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author alvar
 */
public class PuestoDAOSavePuestoTest {
    public PuestoDAOSavePuestoTest(){
    }
   @Test
public void testSavePuesto() throws Exception {
PuestoDAO instance = new PuestoDAO();
Puesto puesto = new Puesto();
puesto.setNombre("odontologo");
boolean x = instance.savePuesto(puesto);
assertEquals(true, x);
}
}
