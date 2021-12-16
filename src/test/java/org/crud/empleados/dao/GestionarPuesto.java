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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author alvar
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    org.crud.empleados.dao.PuestoDAOGetPuestosTest.class, 
    org.crud.empleados.dao.EmpleadoDAOSetPuestoTest.class, 
    org.crud.empleados.dao.PuestoDAOPuestoExisteTest.class, 
    org.crud.empleados.dao.PuestoDAOGetPuestoByIdTest.class, 
    org.crud.empleados.dao.PuestoDAODeletePuestoTest.class,
     org.crud.empleados.dao.PuestoDAOSavePuestoTest.class})
public class GestionarPuesto {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
