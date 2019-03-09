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
import javax.ejb.EJB;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


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
    
    
    
   public void exportarContrato(int idempleado) throws ClassNotFoundException, SQLException, JRException, IOException {
       Map<String, Object> parametros = new HashMap<>();
       parametros.put("idempleado", idempleado);
       //conexiones
       Class.forName("com.mysql.jdbc.Driver");
       Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/MEDIUSSOFTWARE", "root", "");
       //Para buscar el reporte
        File buscareporte = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Paginas/ReportesGestionHumana/Contrato.jasper"));
       //Para llenar el reporte
       JasperPrint llenareporte=JasperFillManager.fillReport(buscareporte.getPath(),parametros ,conexion);
       //permitir que el browser habra el archivo
       //Visibilidad del reporte
        HttpServletResponse visualizareporte = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
       //nombre cabecera del reporte
        visualizareporte.addHeader("Content-disposition", "attachment; filename=Contrato.pdf");
        ServletOutputStream stream =visualizareporte.getOutputStream();
        
        //Generar el pdf tipo de formato
        JasperExportManager.exportReportToPdfStream(llenareporte, stream);
        
        //Cierro el response
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
   
  

    
}
