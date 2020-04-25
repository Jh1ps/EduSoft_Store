/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import model.Conexion;
import model.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @Inject
    private Usuario usuario;
    @Inject
    private PersonaController persona;

    public String valida() {
        try {
            String consulta
                    = "select  u.CORREO, u.CONTRASENA, u.PRIVILEGIO, p.ID, p.NOMBRE, p.APELLIDO1, p.APELLIDO2, p.CORREO, p.TELEFONO, p.DIRECCION, p.ID,  "
                    + "i.NOMBRE from usuario u, persona p, institucion i where u.CORREO=? and u.IDPERSONA ="
                    + " p.ID and p.IDINSTITUCION = i.ID";
            PreparedStatement sentencia
                    = Conexion
                            .getConexion()
                            .prepareStatement(consulta);
            sentencia.setString(1, usuario.getCorreo());
            ResultSet rs = sentencia.executeQuery();
            if (rs != null
                    && rs.next()
                    && rs.getString(2).equals(usuario.getPw())) {
                usuario.setPrivilegio(rs.getString(3));
                persona.setId(Integer.parseInt(rs.getString(4)));
                persona.setNombre(rs.getString(5));
                persona.setApellido1(rs.getString(6));
                persona.setApellido2(rs.getString(7));
                persona.setCorreo(rs.getString(8));
                persona.setTelefono(rs.getString(9));
                persona.setDireccion(rs.getString(10));
                persona.setIdInstitucion(Integer.parseInt(rs.getString(11)));
                return "compra.xhtml";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuario.setCorreo("");
        usuario.setPw("");
        return "index.xhtml";
    }
    
    public String verifica(){
        System.out.println(usuario.getPrivilegio());
        if("respaldo".equals(usuario.getPrivilegio())){
        return "respaldo.xhtml";
    }else{
           return "compra.xhtml"; 
        }
    }
}
