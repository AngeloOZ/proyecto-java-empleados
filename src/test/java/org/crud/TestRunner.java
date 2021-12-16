/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.crud;

import org.crud.empleados.dao.GestionarDB;
import org.crud.empleados.dao.GestionarEmpleado;
import org.crud.empleados.dao.GestionarEmpleadoImagen;
import org.crud.empleados.dao.GestionarPuesto;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(GestionarDB.class);
      Result result1=JUnitCore.runClasses(GestionarEmpleado.class);
      Result result2=JUnitCore.runClasses(GestionarEmpleadoImagen.class);
      Result result3=JUnitCore.runClasses(GestionarPuesto.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
      System.out.println(result1.wasSuccessful());
      System.out.println(result2.wasSuccessful());
      System.out.println(result3.wasSuccessful());
   }
}
