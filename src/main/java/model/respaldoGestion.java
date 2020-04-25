/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


/**
 *
 * @author johan
 */
@Named(value = "respaldoGestion")
@SessionScoped
public class respaldoGestion implements Serializable {

    /**
     * Creates a new instance of respaldoGestion
     */
    public respaldoGestion() {
    }

    public respaldoGestion(String TiraJson) {
        this.TiraJson = TiraJson;
    }
    
    
    private String TiraJson;

    public String getTiraJson() {
        return TiraJson;
    }

    public void setTiraJson(String TiraJson) {
        this.TiraJson = TiraJson;
    }
    
    public String jsonCompra(String base){
        if("compra".equals(base)){
             this.setTiraJson(generarCompra());
             return this.getTiraJson();
        }else{
            return generarCompra();
        }
    }
    
    public String jsonDatoPago(String base){
        if("datopago".equals(base)){
             this.setTiraJson(generarDatoPago());
             return this.getTiraJson();
        }else{
            return generarDatoPago();
        }
    }
    public String jsonFuente(String base){
        if("fuente".equals(base)){
             this.setTiraJson(generarFuente());
             return this.getTiraJson();
        }else{
            return generarFuente();
        }
    }
    public String jsonFuenteEbook(String base){
        if("fuenteebook".equals(base)){
             this.setTiraJson(generarFuenteEbook());
             return this.getTiraJson();
        }else{
            return generarFuenteEbook();
        }
    }
    public String jsonFuenteSoftware(String base){
        if("fuentesoftware".equals(base)){
             this.setTiraJson(generarFuenteSoftware());
             return this.getTiraJson();
        }else{
            return generarFuenteSoftware();
        }
    }
    
    public String jsonInstitucion(String base){
        if("institucion".equals(base)){
             this.setTiraJson(generarInstitucion());
             return this.getTiraJson();
        }else{
            return generarInstitucion();
        }
    }
    
    public String jsonPersona(String base){
        if("persona".equals(base)){
             this.setTiraJson(generarPersona());
             return this.getTiraJson();
        }else{
            return generarPersona();
        }
    }
    
     public String jsonProdebook(String base){
        if("prodebook".equals(base)){
             this.setTiraJson(generarProdEbook());
             return this.getTiraJson();
        }else{
            return generarProdEbook();
        }
    } 
     
     public String jsonProdsoftware(String base){
        if("prodsoftware".equals(base)){
             this.setTiraJson(generarProdSoftware());
             return this.getTiraJson();
        }else{
            return generarProdSoftware();
        }
    }
     
     public String jsonTransaccion(String base){
        if("transaccion".equals(base)){
             this.setTiraJson(generarTransaccion());
             return this.getTiraJson();
        }else{
            return generarTransaccion();
        }
    }
     
     public String jsonUsuario(String base){
        if("usuario".equals(base)){
             this.setTiraJson(generarUsuario());
             return this.getTiraJson();
        }else{
            return generarUsuario();
        }
    }

    public static String generarCompra() {
        Compra compra = new Compra();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_COMPRA);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                compra.setNumerocompra(Integer.parseInt(rs.getString(1)));
                if (rs.getString(2) != null) {
                compra.setIdsoft(Integer.parseInt(rs.getString(2)));
                } else {
                    compra.setIdebook(0);
                }
                if (rs.getString(3) != null) {
                    compra.setIdebook(Integer.parseInt(rs.getString(3)));
                } else {
                    compra.setIdebook(0);
                }
                compra.setIdpersona(Integer.parseInt(rs.getString(4)));
                compra.setIddatopago(Integer.parseInt(rs.getString(5)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Numero de Compra", compra.getNumerocompra()).
                        add("Id software", compra.getIdsoft()).
                        add("Id Ebook", compra.getIdebook()).
                        add("Id persona", compra.getIdpersona()).
                        add("Id de pago", compra.getIddatopago()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
                    
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
    
    public static String generarDatoPago() {
        datoPago DatoPago = new datoPago();
        String tiraJson = "";String Exp;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_DATOPAGO);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                DatoPago.setId(Integer.parseInt(rs.getString(1)));
                DatoPago.setN_tarjeta(Integer.parseInt(rs.getString(2)));
                DatoPago.setExp(rs.getDate(3));
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Exp = formato.format(DatoPago.getExp());
                DatoPago.setCvv(Integer.parseInt(rs.getString(4)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id", DatoPago.getId()).
                        add("Numero de tarjeta", DatoPago.getN_tarjeta()).
                        add("Exp", Exp).
                        add("CVV", DatoPago.getCvv()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
    
     public static String generarFuente() {
        Fuente fuente = new Fuente();
        String tiraJson = "";String Exp;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_FUENTE);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                fuente.setId(Integer.parseInt(rs.getString(1)));
                fuente.setNombrefuente(rs.getString(2));
                fuente.setDescripcion(rs.getString(3));
                fuente.setEnlace(rs.getString(4));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id", fuente.getId()).
                        add("Nombre de fuente", fuente.getNombrefuente()).
                        add("Descripción", fuente.getDescripcion()).
                        add("Enlace", fuente.getEnlace()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarFuenteEbook() {
        FuenteEbook fuenteEbook = new FuenteEbook();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_FUENTE_EBOOK);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                fuenteEbook.setIdarticulo(Integer.parseInt(rs.getString(1)));
                fuenteEbook.setIdebook(Integer.parseInt(rs.getString(2)));
                fuenteEbook.setIdfuente(Integer.parseInt(rs.getString(3)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", fuenteEbook.getIdarticulo()).
                        add("Id de ebook", fuenteEbook.getIdebook()).
                        add("Id de fuente", fuenteEbook.getIdfuente()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarFuenteSoftware() {
        FuenteSoftware fuenteEbook = new FuenteSoftware();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_FUENTE_SOFTWARE);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                fuenteEbook.setIdarticulo(Integer.parseInt(rs.getString(1)));
                fuenteEbook.setIdsoftware(Integer.parseInt(rs.getString(2)));
                fuenteEbook.setIdfuente(Integer.parseInt(rs.getString(3)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", fuenteEbook.getIdarticulo()).
                        add("Id de software", fuenteEbook.getIdsoftware()).
                        add("Id de fuente", fuenteEbook.getIdfuente()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarInstitucion() {
        Institucion institucion = new Institucion();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_INSTITUCION);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                institucion.setId(Integer.parseInt(rs.getString(1)));
                institucion.setNombre(rs.getString(2));
                institucion.setProvincia(rs.getString(3));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", institucion.getId()).
                        add("Id de software", institucion.getNombre()).
                        add("Id de fuente", institucion.getProvincia()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarPersona() {
        Persona persona = new Persona();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_PERSONA);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                persona.setId(Integer.parseInt(rs.getString(1)));
                persona.setNombre(rs.getString(2));
                persona.setApellido1(rs.getString(3));
                persona.setApellido2(rs.getString(4));
                persona.setTelefono(rs.getString(5));
                persona.setCorreo(rs.getString(6));
                persona.setDireccion(rs.getString(7));
                persona.setIdInstitucion(Integer.parseInt(rs.getString(8)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", persona.getId()).
                        add("Nombre", persona.getNombre()).
                        add("Apellido 1", persona.getApellido1()).
                        add("Apellido 2", persona.getApellido2()).
                        add("Telefono", persona.getTelefono()).
                        add("Correo", persona.getCorreo()).
                        add("Direccion", persona.getDireccion()).
                        add("Id de institución", persona.getIdInstitucion()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarProdEbook() {
        prodEbook prodebook = new prodEbook();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_PRODEBOOK);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                prodebook.setId(Integer.parseInt(rs.getString(1)));
                prodebook.setNombre(rs.getString(2));
                prodebook.setDescripcion(rs.getString(3));
                prodebook.setTamano(rs.getString(4));
                prodebook.setPrecio(Double.parseDouble(rs.getString(5)));
                prodebook.setDisponibilidad(rs.getString(6).charAt(0));
                prodebook.setCategoria(rs.getString(7));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", prodebook.getId()).
                        add("Nombre", prodebook.getNombre()).
                        add("Apellido 1", prodebook.getDescripcion()).
                        add("Apellido 2", prodebook.getTamano()).
                        add("Telefono", prodebook.getPrecio()).
                        add("Correo", prodebook.getDisponibilidad()).
                        add("Direccion", prodebook.getCategoria()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarProdSoftware() {
        prodSoftware prodsoftware = new prodSoftware();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_PRODSOFTWARE);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                prodsoftware.setId(Integer.parseInt(rs.getString(1)));
                prodsoftware.setNombre(rs.getString(2));
                prodsoftware.setDescripcion(rs.getString(3));
                prodsoftware.setTamano(rs.getString(4));
                prodsoftware.setPrecio(Double.parseDouble(rs.getString(5)));
                prodsoftware.setDisponibilidad(rs.getString(6).charAt(0));
                prodsoftware.setVersion(rs.getString(7));
                prodsoftware.setCategoria(rs.getString(8));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id de artículo", prodsoftware.getId()).
                        add("Nombre", prodsoftware.getNombre()).
                        add("Apellido 1", prodsoftware.getDescripcion()).
                        add("Apellido 2", prodsoftware.getTamano()).
                        add("Telefono", prodsoftware.getPrecio()).
                        add("Correo", prodsoftware.getDisponibilidad()).
                        add("Versión", prodsoftware.getVersion()).
                        add("Direccion", prodsoftware.getCategoria()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarTransaccion() {
        Transaccion transaccion = new Transaccion();
        String tiraJson = "";String fecha;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_TRANSACCION);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                transaccion.setId(Integer.parseInt(rs.getString(1)));
                transaccion.setFecha(rs.getDate(2));
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                fecha = formato.format(transaccion.getFecha());
                transaccion.setTipo(rs.getString(3));
                transaccion.setNumerocompra(Integer.parseInt(rs.getString(4)));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id", transaccion.getId()).
                        add("Fecha", fecha).
                        add("Tipo", transaccion.getTipo()).
                        add("Numero de compra", transaccion.getNumerocompra()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
     public static String generarUsuario() {
        Usuario usuario = new Usuario();
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_USUARIO);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                usuario.setCorreo(rs.getString(1));
                usuario.setPw(rs.getString(2));
                usuario.setIdpersona(Integer.parseInt(rs.getString(3)));
                usuario.setPrivilegio(rs.getString(4));
                JsonObjectBuilder objetoJsonB = Json.createObjectBuilder();
                JsonObject json = objetoJsonB.
                        add("Id", usuario.getCorreo()).
                        add("Fecha", usuario.getPw()).
                        add("Tipo", usuario.getIdpersona()).
                        add("Numero de compra", usuario.getPrivilegio()).build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(json);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {

            Logger.getLogger(respaldoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

    private static final String SQL_SELECT_COMPRA
            = "select * from compra";
    private static final String SQL_SELECT_DATOPAGO
            = "select * from datopago";
    private static final String SQL_SELECT_FUENTE
            = "select * from fuente";
    private static final String SQL_SELECT_FUENTE_EBOOK
            = "select * from fuente_ebook";
    private static final String SQL_SELECT_FUENTE_SOFTWARE
            = "select * from fuente_software";
    private static final String SQL_SELECT_INSTITUCION
            = "select * from institucion";
    private static final String SQL_SELECT_PERSONA
            = "select * from persona";
    private static final String SQL_SELECT_PRODEBOOK
            = "select * from prodebook";
    private static final String SQL_SELECT_PRODSOFTWARE
            = "select * from prodsoftware";
    private static final String SQL_SELECT_TRANSACCION
            = "select * from transaccion";
    private static final String SQL_SELECT_USUARIO
            = "select * from usuario";

}
    
