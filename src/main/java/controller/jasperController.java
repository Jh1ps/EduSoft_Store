/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import model.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author johan
 */
@Named(value = "jasperController")
@RequestScoped
public class jasperController {

    /**
     * Creates a new instance of jasperController
     */
    public jasperController() {
    }
    public void verPdf() throws IOException {
        BufferedImage image = ImageIO.read(getClass().getResource("/images/marcaJasper.png"));
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("imagen", image);

        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/reporteSoftware.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), parametros, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
