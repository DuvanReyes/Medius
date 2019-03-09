/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Ciudad;
import facades.CiudadFacade;
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
@Named(value = "imporCiudad")
@ViewScoped
public class ImporCiudad  implements Serializable{

    @EJB
    CiudadFacade ciudadFacade;
    
    public ImporCiudad() {
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        InputStreamReader reader = new InputStreamReader(event.getFile().getInputstream());
        BufferedReader br = new BufferedReader(reader);
        String line;
         
         while ((line =br.readLine()) != null ) {
             line = line.replaceAll("\"", "");
             
             String[] separar = line.split(",");
           
             Ciudad ciudadNew = new Ciudad();
             ciudadNew.setIdciudad(Integer.parseInt(separar[0]));
             ciudadNew.setNumerociudad(Integer.parseInt(separar[1]));
             ciudadNew.setCiudadnombre(separar[2]);
             try {
                 ciudadFacade.create(ciudadNew);
             } catch (Exception e) {
             FacesMessage message = new FacesMessage("Succesful", ciudadNew.getCiudadnombre()+"ya se necuentra registrada");
            FacesContext.getCurrentInstance().addMessage(null, message);
             }
             
         }
        
          
    }
    
}
