/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johan
 */
public class PersonaGestion {
    
    private static final String SQL_UPDATE_PERSONA
            = "update persona set nombre=?, apellido1=?, apellido2=?,"
            + "telefono=?, correo=?, direccion=? where id=?";
    
    private static final String SQL_SELECT_PERSONA
            = "select * from persona where id=?";
    
    public static boolean modificar(Persona persona) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATE_PERSONA);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido1());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setString(5, persona.getCorreo());
            sentencia.setString(6, persona.getDireccion());
            sentencia.setInt(7, persona.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Persona getPersona(String id) {
        Persona persona = null;
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(SQL_SELECT_PERSONA);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos != null && datos.next()) {
                persona = new Persona(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7),
                        datos.getInt(8)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }
    
}
