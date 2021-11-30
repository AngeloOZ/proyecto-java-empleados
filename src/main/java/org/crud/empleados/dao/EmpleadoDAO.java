package org.crud.empleados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.crud.empleados.domain.Empleado;

public class EmpleadoDAO {

    private static final String GET_EMPLEADOS = "SELECT id, nombre, correo , p.puesto_id, nombre_puesto FROM empleado e left join puesto p on (e.puesto_id = p.puesto_id) ";

    private static final String GET_EMPLEADO_BY_ID = "SELECT id, nombre, correo ,  p.puesto_id, nombre_puesto FROM empleado e left join puesto p on (e.puesto_id = p.puesto_id) where id = ?";

    private static final String SAVE_EMPLEADO = "insert into empleado (id,nombre, correo) values ( nextval('empleado_id_seq') ,?, ?)";

    private static final String UPDATE_EMPLEADO = "update empleado set nombre = ? , correo = ? where id = ?";

    private static final String DELETE_EMPLEADO = "delete from empleado where id = ?";
    
    private static final String SET_PUESTO = "update empleado set puesto_id = ? where id =? ";

    public List<Empleado> getEmpleados() throws ClassNotFoundException, SQLException {
        List<Empleado> empleados = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(GET_EMPLEADOS);
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getLong("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setCorreo(resultSet.getString("correo"));
                empleado.setPuesto( resultSet.getLong("puesto_id") );
                empleado.setPuestoNombre( resultSet.getString("nombre_puesto") );
                empleados.add(empleado);
            }
        } finally {

        }
        return empleados;
    }

    public Empleado getEmpleado(long id) throws ClassNotFoundException, SQLException {
        Empleado empleado = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(GET_EMPLEADO_BY_ID);
            psmt.setLong(1, id);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                empleado = new Empleado();
                empleado.setId(resultSet.getLong("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setCorreo(resultSet.getString("correo"));
                empleado.setPuesto( resultSet.getLong("puesto_id") );
                empleado.setPuestoNombre(resultSet.getString("nombre_puesto"));
            }
        } finally {

        }
        return empleado;
    }

    public boolean saveEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException {
        boolean success = false;
        if (empleado != null) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                PreparedStatement psmt = null;
                if (empleado.getId() == null) {
                    psmt = connection.prepareStatement(SAVE_EMPLEADO);
                    psmt.setString(1, empleado.getNombre());
                    psmt.setString(2, empleado.getCorreo());
                } else {
                    psmt = connection.prepareStatement(UPDATE_EMPLEADO);
                    psmt.setString(1, empleado.getNombre());
                    psmt.setString(2, empleado.getCorreo());
                    psmt.setLong(3, empleado.getId());
                }
                psmt.executeUpdate();
                success = true;
            } finally {

            }
        }
        return success;
    }

    public boolean deleteEmpleado(Long id) throws ClassNotFoundException, SQLException {
        boolean success = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(DELETE_EMPLEADO);
            psmt.setLong(1, id);
            psmt.executeUpdate();
            success = true;
        } finally {

        }
        return success;
    }
    
    
    public void setPuestoToEmpleado( Long empleadoId, Long puestoId ) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement( SET_PUESTO );
            psmt.setLong(1, puestoId);
            psmt.setLong(2, empleadoId );
            psmt.execute();
        } finally {

        }
    }

}
