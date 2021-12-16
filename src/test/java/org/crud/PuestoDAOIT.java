package org.crud;

import java.util.ArrayList;
import java.util.List;
import org.crud.empleados.dao.DatabaseConnection;
import org.crud.empleados.dao.PuestoDAO;
import org.crud.empleados.domain.Empleado;
import org.crud.empleados.domain.Puesto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author chimb
 */
public class PuestoDAOIT {
    
     PuestoDAO puestodao;
     DatabaseConnection conecction;
     Puesto puesto ;
    List<Puesto> puestos;
     Empleado empleado;
      public PuestoDAOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
            long num = 1;
             long num1 = 2;
    puestodao = new PuestoDAO();
    conecction = Mockito.mock(DatabaseConnection.class);
    puesto = Mockito.mock(Puesto.class);
    // save empleado dao 
    empleado =Mockito.mock(Empleado.class);
    
    
    when(puesto.getNombre()).thenReturn("tesorero3");
    when(puesto.getId()).thenReturn(num);
    
      
    puestos = new ArrayList<>();
  
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
       
       
        puestodao.getPuestos();
              
        
        
    }


    /**
     * Test of savePuesto method, of class PuestoDAO.
     */
    @Test
    public void testSavePuesto() throws Exception {
        System.out.println("savePuesto");
          assertTrue("Esta variable no es false: ", puestodao.savePuesto(puesto));
              
    }

    /**
     * Test of deletePuesto method, of class PuestoDAO.
     */
    @Test
    public void testDeletePuesto() throws Exception {
        System.out.println("deletePuesto");
        long num1= 2;
              
        assertTrue("Esta variable no es false: ", puestodao.deletePuesto(num1));
    }

    /**
     * Test of puestoExiste method, of class PuestoDAO.
     */
    
    @Test
    public void testPuestoExiste() throws Exception {
        /*
      
        */
         assertTrue("Esta variable no es false: ", puestodao.puestoExiste("tesorero3"));
        
        
    }
    
}

