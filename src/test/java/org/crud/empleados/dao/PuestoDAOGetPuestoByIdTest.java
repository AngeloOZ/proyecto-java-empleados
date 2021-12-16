/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.crud.empleados.domain.Puesto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvar
 */
public class PuestoDAOGetPuestoByIdTest {
    
    public PuestoDAOGetPuestoByIdTest() {
    }
@Test
    public void testGetPuestoById() {
    System.out.println("getPuestoById");
    Long id = null;
    PuestoDAO instance = new PuestoDAO();
    Puesto expResult = null;
    Puesto result = instance.getPuestoById(id);
    assertEquals(expResult, result);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
