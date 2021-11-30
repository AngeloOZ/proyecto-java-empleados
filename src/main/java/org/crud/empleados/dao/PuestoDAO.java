package org.crud.empleados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.crud.empleados.domain.Puesto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuestoDAO {

    private static final Logger logger = LoggerFactory.getLogger(PuestoDAO.class);

    private static final String GET_PUESTOS = "select puesto_id, nombre_puesto from puesto";

    private static final String GET_PUESTO_BY_ID = "select puesto_id, nombre_puesto from puesto where puesto_id = ?";

    private static final String SAVE_PUESTO = "insert into puesto( puesto_id, nombre_puesto ) values ( nextval('puesto_id_seq') , ?)  ";

    private static final String UPDATE_PUESTO = "update puesto set nombre_puesto = ? where puesto_id = ?";

    private static final String DELETE_PUESTO = "delete from puesto where puesto_id = ?";

    private static final String PUESTO_EXISTE = "select count(*) as count from puesto where nombre_puesto = ?";

    public List<Puesto> getPuestos() {
        List<Puesto> puestos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(GET_PUESTOS);
            while (resultSet.next()) {
                Puesto puesto = new Puesto();
                puesto.setId(resultSet.getLong("puesto_id"));
                puesto.setNombre(resultSet.getString("nombre_puesto"));
                puestos.add(puesto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return puestos;
    }

    public Puesto getPuestoById(Long id) {
        Puesto puesto = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(GET_PUESTO_BY_ID);
            psmt.setLong(1, id);
            ResultSet resultSet = psmt.executeQuery(GET_PUESTO_BY_ID);
            while (resultSet.next()) {
                puesto = new Puesto();
                puesto.setId(resultSet.getLong("puesto_id"));
                puesto.setNombre(resultSet.getString("nombre_puesto"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return puesto;
    }

    public boolean savePuesto(Puesto puesto) throws Exception {
        boolean success = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (puesto.getId() == null) {
                PreparedStatement psmt = connection.prepareStatement(SAVE_PUESTO);
                psmt.setString(1, puesto.getNombre());
                psmt.execute();
            } else {
                PreparedStatement psmt = connection.prepareStatement(UPDATE_PUESTO);
                psmt.setString(1, puesto.getNombre());
                psmt.setLong(2, puesto.getId());
                psmt.execute();
            }
            success = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return success;
    }

    public boolean deletePuesto(Long id) throws Exception {
        boolean success = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(DELETE_PUESTO);
            psmt.setLong(1, id);
            psmt.execute();
            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return success;
    }

    public boolean puestoExiste(String nombrePuesto) throws Exception {
        boolean existe = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement psmt = connection.prepareStatement(PUESTO_EXISTE);
            psmt.setString(1, nombrePuesto);
            ResultSet resultSet = psmt.executeQuery();
            int count  = 0;
            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
            existe = count > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return existe;
    }

}
