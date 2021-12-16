/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crud.empleados.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Angello Ordo�ez
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    org.crud.empleados.dao.EmpleadoDAOInsertTest.class,
    org.crud.empleados.dao.EmpleadoDAODeleteTest.class,
    org.crud.empleados.dao.EmpleadoDAOSetPuestoTest.class,
    org.crud.empleados.dao.EmpleadoDAOGetEmpleadoTest.class })
public class GestionarEmpleado {
    
}
