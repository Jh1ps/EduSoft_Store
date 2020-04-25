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
@Named(value = "compra")
@SessionScoped
public class Compra implements Serializable {

        private int numerocompra;
        private int idsoft;
        private int idebook;
        private int idpersona;
        private int iddatopago;

    public int getNumerocompra() {
        return numerocompra;
    }

    public void setNumerocompra(int numerocompra) {
        this.numerocompra = numerocompra;
    }

    public int getIdsoft() {
        return idsoft;
    }

    public void setIdsoft(int idsoft) {
        this.idsoft = idsoft;
    }

    public int getIdebook() {
        return idebook;
    }

    public void setIdebook(int idebook) {
        this.idebook = idebook;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getIddatopago() {
        return iddatopago;
    }

    public void setIddatopago(int iddatopago) {
        this.iddatopago = iddatopago;
    }

    public Compra(int numerocompra, int idsoft, int idebook, int idpersona, int iddatopago) {
        this.numerocompra = numerocompra;
        this.idsoft = idsoft;
        this.idebook = idebook;
        this.idpersona = idpersona;
        this.iddatopago = iddatopago;
    }
        
    public Compra() {
    }
    
}
