/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.crud;

import org.crud.empleados.dao.GestionarDB;
import org.crud.empleados.dao.GestionarEmpleado;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(GestionarDB.class);
      Result result1=JUnitCore.runClasses(GestionarEmpleado.class);

      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
      System.out.println(result1.wasSuccessful());
   }
}
