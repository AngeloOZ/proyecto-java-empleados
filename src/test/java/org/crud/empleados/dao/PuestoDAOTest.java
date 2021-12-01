/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.crud.empleados.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class PuestoDAOTest {

public PuestoDAOTest() {
}

@BeforeClass
public static void setUpClass() {
}

@AfterClass
public static void tearDownClass() {
}

@Before
public void setUp() {
}

@After
public void tearDown() {
}
/**
* Test of getPuestos method, of class PuestoDAO.
*/
@Test
public void testGetPuestos() {
System.out.println("getPuestos");
PuestoDAO puestoDAO = new PuestoDAO();
boolean expResult = false;
List<Puesto> result =puestoDAO.getPuestos();
boolean x = result.isEmpty();
assertEquals(expResult, x);
}
/**
* Test of getPuestoById method, of class PuestoDAO.
*/
@Test
public void testGetPuestoById() {
System.out.println("getPuestoById");
Long id = null;
PuestoDAO instance = new PuestoDAO();
Puesto expResult = null;
Puesto result = instance.getPuestoById(id);
assertEquals(expResult, result);
}
/**
* Test of savePuesto method, of class PuestoDAO.
@throws java.lang.Exception
*/
@Test
public void testSavePuesto() throws Exception {
System.out.println("savePuesto");
PuestoDAO puestoDAO = new PuestoDAO();
Puesto puesto = new Puesto();
puesto.setNombre("presidente");
boolean x = puestoDAO.savePuesto(puesto);
assertEquals(true, x);
}
/**
* Test of deletePuesto method, of class PuestoDAO.
*/
@Test
public void testDeletePuesto() throws Exception {
System.out.println("deletePuesto");
long id = 27;
PuestoDAO puestoDAO = new PuestoDAO();
boolean x=puestoDAO.deletePuesto(id);
boolean expResult = true;
assertEquals(expResult, x);
}
/**
* Test of puestoExiste method, of class PuestoDAO.
*/
@Test
public void testPuestoExiste() throws Exception {
System.out.println("puestoExiste");
PuestoDAO puestoDAO = new PuestoDAO();
boolean x=puestoDAO.puestoExiste("presidente");
boolean expResult = true;
assertEquals(expResult, x);
}

}