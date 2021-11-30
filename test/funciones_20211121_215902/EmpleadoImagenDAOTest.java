package funciones_20211121_215902;

import entidades.*;
import funciones.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmpleadoImagenDAOTest {

    public EmpleadoImagenDAOTest() {
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

@Test
    public void testempleado_imagen() throws Exception {
        System.out.println("empleado_imagen");
        String metodo = "empleado_imagen";
        String clase = "EmpleadoImagenDAO";
        Date vinicio = new Date();
      empleado_id param1 = new empleado_id();
      miniatura param2 = new miniatura();
        Boolean expResult = true;
        into result = null;
        try {
            result = EmpleadoImagenDAO.empleado_imagen(param1,param2);
        } catch (Exception e) {
        result = null;
        }
        Boolean res = false;
        if (result == 0) {
            res = true;
        } else {
            res = false;
        }
        assertEquals(expResult, res);
        Date vfinal = new Date();
        long duracion = Util.DiferenciaFechas(vinicio, vfinal);
        Util.exportar(clase, metodo, duracion);
    }}
