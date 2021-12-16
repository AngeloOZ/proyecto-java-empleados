/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

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
public class PuestoDAODeletePuestoTest {
    
    public PuestoDAODeletePuestoTest() {
    }
    
@Test
public void testDeletePuesto() throws Exception {
System.out.println("deletePuesto");
long id = 2;
PuestoDAO instance = new PuestoDAO();
boolean x = instance.deletePuesto(id);
boolean expResult = true;
assertEquals(expResult, x);
}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
