/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author johan
 */
@Named(value = "fuente")
@SessionScoped
public class Fuente implements Serializable {

    private int id;
    private String nombrefuente;
    private String descripcion;
    private String enlace;

    public Fuente(int id, String nombrefuente, String descripcion, String enlace) {
        this.id = id;
        this.nombrefuente = nombrefuente;
        this.descripcion = descripcion;
        this.enlace = enlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrefuente() {
        return nombrefuente;
    }

    public void setNombrefuente(String nombrefuente) {
        this.nombrefuente = nombrefuente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    
    public Fuente() {
    }
    
}
