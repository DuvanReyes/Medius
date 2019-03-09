/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Statement;
import entidades.Bodegainsumo;
import entidades.Producto;
import facades.BodegainsumoFacade;
import facades.ProductoFacade;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author Arco02
 */
@Named(value = "reportesContolador")
@SessionScoped
public class ReportesContolador implements Serializable {

    @EJB 
    BodegainsumoFacade bodegainsumofacade;
    Bodegainsumo bodegainsumo;
    @EJB
    ProductoFacade productofacade;
    Producto producto;
    
    private String data = "";
    private String data2 = "";
    
    
    public ReportesContolador() {
        bodegainsumo = new Bodegainsumo();
        producto = new Producto();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    

public String datosGraficaUno() throws SQLException{
    
 String x="",y="";
      
      try
      {
         Class.forName("org.gjt.mm.mysql.Driver");
         Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/MEDIUSSOFTWARE", "root", "");
         Statement st = conexion.createStatement();
         ResultSet rs = st.executeQuery("select ins.nombreinsumo, dbi.bodegainsumototal\n" +
"from bodegainsumo dbi\n" +
"inner join insumo ins on  dbi.insumoid=ins.idinsumo\n" +
"inner join bodega bd on dbi.bodegaid=bd.idbodega where dbi.bodegainsumototal<ins.insumocantidadminima;" );
         while (rs.next()){
            x = rs.getString("nombreinsumo");
            y = rs.getString("bodegainsumototal");
         }
         rs.close();
         st.close();
         conexion.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      data=data + "{y: "+y+", name:\""+x+"\", exploded: true},";
      return data;
  
     }



    public String datosGraficaDos(){
         for (Producto pro: productofacade.findAll()) {
             data2 = data2 + "{y: "+pro.getProductolineaid()+", name:\""+pro.getNombreproducto()+"\", exploded: true},";
         }
     return data2;
     }
    
    
    
   public void exportar(int idinsumo, String nombreinsumo) throws ClassNotFoundException, SQLException, JRException, IOException {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            File jasper = new File(ec.getRealPath("../Reportes/report1.jasper"));
            Map<String, Object> params = new HashMap<>();
            params.put("pIn", idinsumo);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_carrito", "root", "");
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), params, conexion);
            HttpServletResponse hsr = (HttpServletResponse) ec.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Procutos"+nombreinsumo+".pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            fc.responseComplete();
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }
    
}
