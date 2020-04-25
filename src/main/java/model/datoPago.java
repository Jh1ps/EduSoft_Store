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
@Named(value = "datoPago")
@SessionScoped
public class datoPago implements Serializable {

    private int id;
    private int n_tarjeta;
    private Date exp;
    private int cvv;

    public datoPago(int id, int n_tarjeta, Date exp, int cvv) {
        this.id = id;
        this.n_tarjeta = n_tarjeta;
        this.exp = exp;
        this.cvv = cvv;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_tarjeta() {
        return n_tarjeta;
    }

    public void setN_tarjeta(int n_tarjeta) {
        this.n_tarjeta = n_tarjeta;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
    
    
    public datoPago() {
    }
    
}
