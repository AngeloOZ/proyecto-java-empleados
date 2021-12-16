
package org.crud.empleados.dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erik
 */
public class EmpleadoDAOGetImagenTest {
    
    public EmpleadoDAOGetImagenTest() {
    
    
    }
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        long id = 2;
        boolean x = false;
        EmpleadoImagenDAO empleadoImagenDAO = new EmpleadoImagenDAO();
        String imagenData = empleadoImagenDAO.getImagen(id);
        if (imagenData != null) {
        x=true;
        }
        assertEquals(true,x);
        }
    
}
