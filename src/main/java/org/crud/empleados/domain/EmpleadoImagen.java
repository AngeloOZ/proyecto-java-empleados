package org.crud.empleados.domain;

public class EmpleadoImagen {
    
    private Long id;
    
    private String miniature;

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
     * @return the miniature
     */
    public String getMiniature() {
        return miniature;
    }

    /**
     * @param miniature the miniature to set
     */
    public void setMiniature(String miniature) {
        this.miniature = miniature;
    }    

}
