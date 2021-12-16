
package org.crud.empleados.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;
import org.crud.empleados.domain.EmpleadoImagen;
import org.crud.helpers.Constants;
import org.crud.helpers.ImageScaling;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erik
 */
public class EmpleadoDAOSaveImagenTest {
    
    public EmpleadoDAOSaveImagenTest() {    
    }
    
    @Test
    public void testSaveEmpleadoImagen() throws IOException {
        File selectedFile = new File(".\\gato.jpg");
        ImageScaling imageScaling = new ImageScaling(Constants.SCALE_FACTOR);
        byte[] data = Files.readAllBytes(Paths.get(selectedFile.toURI()));
        byte[] newData = imageScaling.scaleImage(data);
        String byteData = new String(Base64.encodeBase64(newData), "utf-8");

        EmpleadoImagenDAO empleadoImagenDAO = new EmpleadoImagenDAO();
        long id = 2;
        EmpleadoImagen empleadoImagen = new EmpleadoImagen();
        empleadoImagen.setId(id);
        empleadoImagen.setMiniature(byteData);
        empleadoImagenDAO.saveEmpleadoImagen(empleadoImagen);

        String x = empleadoImagenDAO.getImagen(id);

        assertEquals("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",x);
        }

    
}
