/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.respaldoGestion;

/**
 *
 * @author johan
 */
@Named(value = "respaldoController")
@SessionScoped
public class respaldoController implements Serializable {

    public static void respaldo() {
        ZipOutputStream out = null;
        String json = respaldoGestion.generarCompra();
        String jsondatoPago = respaldoGestion.generarDatoPago();
        String jsonFuente = respaldoGestion.generarFuente();
        String jsonFuenteEbook = respaldoGestion.generarFuenteEbook();
        String jsonFuenteSoftare = respaldoGestion.generarFuenteSoftware();
        String jsonInstitucion = respaldoGestion.generarInstitucion() ;
        String jsonPersona = respaldoGestion.generarPersona() ;
        String jsonprodEbook = respaldoGestion.generarProdEbook();
        String jsonprodSoftware = respaldoGestion.generarProdSoftware();
        String jsonTransaccion = respaldoGestion.generarTransaccion();
        String jsonUsuario = respaldoGestion.generarUsuario();
        
        try {
            File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo") + "respaldo.zip");
            out = new ZipOutputStream(new FileOutputStream(f));

            ZipEntry c = new ZipEntry("compra.json");
            ZipEntry dp = new ZipEntry("datoPago.json");
            ZipEntry fu = new ZipEntry("fuente.json");
            ZipEntry fe = new ZipEntry("fuenteEbook.json");
            ZipEntry fs = new ZipEntry("fuenteSoftware.json");
            ZipEntry i = new ZipEntry("institucion.json");
            ZipEntry p = new ZipEntry("persona.json");
            ZipEntry pe = new ZipEntry("prodEbook");
            ZipEntry ps = new ZipEntry("prodSoftware");
            ZipEntry t = new ZipEntry("transaccion");
            ZipEntry u = new ZipEntry("usuario");


            out.putNextEntry(c);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();

            out.putNextEntry(dp);
            byte[] dataDatoPago = jsondatoPago.getBytes();
            out.write(dataDatoPago, 0, dataDatoPago.length);
            out.closeEntry();

            out.putNextEntry(fu);
            byte[] dataFuente = jsonFuente.getBytes();
            out.write(dataFuente, 0, dataFuente.length);
            out.closeEntry();
            
            out.putNextEntry(fe);
            byte[] dataFuenteEbook = jsonFuenteEbook.getBytes();
            out.write(dataFuenteEbook, 0, dataFuenteEbook.length);
            out.closeEntry();
            
            out.putNextEntry(fs);
            byte[] dataFuenteSoftware = jsonFuenteSoftare.getBytes();
            out.write(dataFuenteSoftware, 0, dataFuenteSoftware.length);
            out.closeEntry();
            
            out.putNextEntry(i);
            byte[] dataInstitucion = jsonInstitucion.getBytes();
            out.write(dataInstitucion, 0, dataInstitucion.length);
            out.closeEntry();
            
            out.putNextEntry(p);
            byte[] dataPersona = jsonPersona.getBytes();
            out.write(dataPersona, 0, dataPersona.length);
            out.closeEntry();
            
            out.putNextEntry(pe);
            byte[] dataprodEbook = jsonprodEbook.getBytes();
            out.write(dataprodEbook, 0, dataprodEbook.length);
            out.closeEntry();
            
            out.putNextEntry(ps);
            byte[] dataprodSoftware = jsonprodSoftware.getBytes();
            out.write(dataprodSoftware, 0, dataprodSoftware.length);
            out.closeEntry();
            
            out.putNextEntry(t);
            byte[] dataTransaccion = jsonTransaccion.getBytes();
            out.write(dataTransaccion, 0, dataTransaccion.length);
            out.closeEntry();
            
            out.putNextEntry(u);
            byte[] dataUsuario = jsonUsuario.getBytes();
            out.write(dataUsuario, 0, dataUsuario.length);
            out.closeEntry();

            out.close();

            //se hace la descarga
            File zipPath = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo") + "respaldo.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/zip");
            respuesta.setHeader("Content-Disposition", "attachment; filename=respaldo.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(respaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(respaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(respaldoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
