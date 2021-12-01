package org.crud;

import org.crud.empleados.dao.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 Hola bola
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.initDatabase();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
    }

}
