/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author johan
 */
@Named(value = "transaccion")
@SessionScoped
public class Transaccion implements Serializable {

    private int id;
    private Date fecha;
    private String tipo;
    private int numerocompra;

    public Transaccion(int id, Date fecha, String tipo, int numerocompra) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.numerocompra = numerocompra;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumerocompra() {
        return numerocompra;
    }

    public void setNumerocompra(int numerocompra) {
        this.numerocompra = numerocompra;
    }
    
    
    
    public Transaccion() {
    }
    
}
