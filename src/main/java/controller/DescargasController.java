
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


@Named(value = "descargaController")
@SessionScoped
public class DescargasController implements Serializable {

    
    public void respaldo(){
        ZipOutputStream out = null;
        String json = "La descarga es con fines ilustrativos.";
        try {
            File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/descarga")+"TuAplicacion.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            
            ZipEntry a = new ZipEntry("Aplicación.exe");
            ZipEntry t = new ZipEntry("Leeme.json");
            
            out.putNextEntry(t);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            
            out.putNextEntry(a);
            out.closeEntry();
            
            out.close();
            
            //se hace la descarga
            File zipPath = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/descarga")+"TuAplicacion.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/zip");
            respuesta.setHeader("Content-Disposition","attachment; filename=TuAplicacion.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DescargasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DescargasController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(DescargasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public DescargasController() {
    }
    
}
