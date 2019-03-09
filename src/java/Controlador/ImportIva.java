/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Reteiva;
import facades.ReteivaFacade;
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
 * @author acer
 */
@Named(value = "importIva")
@ViewScoped
public class ImportIva implements Serializable{

    @EJB
    ReteivaFacade reteivaFacade;
    Reteiva reteiva;
    
    
    public ImportIva() {
        
    }
    
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        InputStreamReader reader = new InputStreamReader(event.getFile().getInputstream());
        BufferedReader br = new BufferedReader(reader);
        String line;
         
         while ((line =br.readLine()) != null ) {
             line = line.replaceAll("\"", "");
             
             String[] separar = line.split(",");
           
             Reteiva  ReteivaNew = new Reteiva();
             ReteivaNew.setIdreteiva(Integer.parseInt(separar[0]));
             ReteivaNew.setNombrereteiva(separar[1]);
             ReteivaNew.setTasareteiva(Integer.parseInt(separar[2]));


             try {
                 reteivaFacade.create(ReteivaNew);
             } catch (Exception e) {
             FacesMessage message = new FacesMessage("Succesful", ReteivaNew.getIdreteiva()+"ya se necuentra registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
             }
             
         }    
    }
    
}
