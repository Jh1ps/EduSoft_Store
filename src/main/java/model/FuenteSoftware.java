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
@Named(value = "fuenteSoftware")
@SessionScoped
public class FuenteSoftware implements Serializable {

    private int idarticulo;
    private int idsoftware;
    private int idfuente;

    public FuenteSoftware(int idarticulo, int idsoftware, int idfuente) {
        this.idarticulo = idarticulo;
        this.idsoftware = idsoftware;
        this.idfuente = idfuente;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIdsoftware() {
        return idsoftware;
    }

    public void setIdsoftware(int idsoftware) {
        this.idsoftware = idsoftware;
    }

    public int getIdfuente() {
        return idfuente;
    }

    public void setIdfuente(int idfuente) {
        this.idfuente = idfuente;
    }
    
    public FuenteSoftware() {
    }
    
}
