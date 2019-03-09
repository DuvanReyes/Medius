/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Insumo;
import facades.InsumoFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Arco02
 */
@Named(value = "imporInsumo")
@ViewScoped
public class ImporInsumo  implements Serializable{

    @EJB InsumoFacade insumofacade;
    
    public ImporInsumo() {
    }
    
   public void handleFileUpload(FileUploadEvent event) throws IOException {
        InputStreamReader reader = new InputStreamReader(event.getFile().getInputstream());
        BufferedReader br = new BufferedReader(reader);
        String line;
         
        while ((line =br.readLine()) != null ) {
             line = line.replaceAll("\"", "");
             
             String[] separar = line.split(",");
           
             Insumo insumoNew = new Insumo();
             insumoNew.setIdinsumo(Integer.parseInt(separar[0]));
             insumoNew.setNombreinsumo(separar[1]);
            //insumoNew.setInsumomarcaid(Integer.parseInt(separar[2]));
             //insumoNew.setInsumoreteivaid(Integer.parseInt(separar[3]));
             insumoNew.setInsumocantidadminima(Integer.parseInt(separar[4]));
             insumoNew.setInsumocantidadmaxima(Integer.parseInt(separar[5]));
             insumoNew.setEstadoinsumo(Integer.parseInt(separar[6]));
             try {
                 insumofacade.create(insumoNew);
             } catch (Exception e) {
             FacesMessage message = new FacesMessage("Succesful", insumoNew.getIdinsumo()+ ", "+insumoNew.getNombreinsumo()+"ya se necuentra registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
             }
             
         }
        
          
    }
    
}
