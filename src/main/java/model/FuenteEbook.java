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
@Named(value = "fuenteEbook")
@SessionScoped
public class FuenteEbook implements Serializable {

    private int idarticulo;
    private int idebook;
    private int idfuente;

    public FuenteEbook(int idarticulo, int idebook, int idfuente) {
        this.idarticulo = idarticulo;
        this.idebook = idebook;
        this.idfuente = idfuente;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIdebook() {
        return idebook;
    }

    public void setIdebook(int idebook) {
        this.idebook = idebook;
    }

    public int getIdfuente() {
        return idfuente;
    }

    public void setIdfuente(int idfuente) {
        this.idfuente = idfuente;
    }
    
    public FuenteEbook() {
    }
    
}
