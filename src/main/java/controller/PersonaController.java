/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Persona;
import model.PersonaGestion;

/**
 *
 * @author johan
 */
@Named(value = "personaController")
@SessionScoped
public class PersonaController extends Persona implements Serializable {

    /**
     * Creates a new instance of PersonaController
     */
    public PersonaController() {
    }
    
    public String modifica() {
        if (PersonaGestion.modificar(this)) {
            return "compra.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el id no existe");
            FacesContext.getCurrentInstance().addMessage(
                    "editaPersonaForm:identificación",
                    mensaje);
            return "perfil.xhtml";
        }
    }
    
}
