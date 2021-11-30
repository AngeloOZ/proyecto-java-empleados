package org.crud.empleados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.crud.empleados.domain.EmpleadoImagen;

public class EmpleadoImagenDAO {
    
    public static final String SAVE_IMAGEN = "insert into empleado_imagen (empleado_id, miniatura) values (?,?) ";
    
    public static final String UPDATE_IMAGEN = "update empleado_imagen set miniatura = ? where empleado_id =?";
    
    public static final String GET_IMAGEN = "SELECT miniatura from empleado_imagen where empleado_id = ? ";

    public void saveEmpleadoImagen(EmpleadoImagen empleadoImagen) {
        String data = this.getImagen( empleadoImagen.getId() );
        if(data == null) {
            try( Connection connection = DatabaseConnection.getConnection() ) {
                PreparedStatement psmt = connection.prepareStatement(SAVE_IMAGEN);
                psmt.setLong(1, empleadoImagen.getId());
                psmt.setString(2, empleadoImagen.getMiniature() );
                psmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }else {
            try( Connection connection = DatabaseConnection.getConnection() ) {
                PreparedStatement psmt = connection.prepareStatement(UPDATE_IMAGEN);
                psmt.setString(1, empleadoImagen.getMiniature());
                psmt.setLong(2, empleadoImagen.getId());
                psmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getImagen(Long id) {
        String imagen = null;
        try( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement psmt = connection.prepareStatement(GET_IMAGEN);
            psmt.setLong(1, id);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()) {
                imagen = rs.getString(1);
            }
        } catch (Exception e) {
            
        }
        return imagen;
    }
    
    
    
    

}
