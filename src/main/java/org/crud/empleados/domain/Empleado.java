package org.crud.empleados.domain;

/**
 * Clase Empleados
 * 
 * @author Carlos Acosta
 *
 */
public class Empleado {

    /**
     * Id de empleado
     */
    private Long id;

    /**
     * Nombre de empleado
     */
    private String nombre;

    /**
     * Correo de empleado
     */
    private String correo;
    
    
    private Long puesto;
    
    
    private String puestoNombre;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the puesto
     */
    public Long getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(Long puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the puestoNombre
     */
    public String getPuestoNombre() {
        return puestoNombre;
    }

    /**
     * @param puestoNombre the puestoNombre to set
     */
    public void setPuestoNombre(String puestoNombre) {
        this.puestoNombre = puestoNombre;
    }

}
