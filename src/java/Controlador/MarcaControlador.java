/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Marca;
import facades.MarcaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "marcaControlador")
@SessionScoped
public class MarcaControlador implements Serializable {

     @EJB MarcaFacade marcafacade;
     private Marca marca;
     
    public MarcaControlador() {
        marca= new Marca();
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    //Inicio de los metodos crud
     public List<Marca> listaMarca(){
        return marcafacade.findAll();
    }
          
    //Registro de un dato
    public String registrar(){
        marcafacade.create(marca);
        marca = new Marca();       
        return "RegistraInsumo?faces-redirect=true";  
      
    }
    
    
    
    //eliminar de un dato
    public String eliminar(Marca m){
        marca = m;
        marcafacade.remove(marca);
        marca = new Marca();
        return "ListaInsumos?faces-redirect=true";
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Marca m){
        marca=m;
        return "ModificaMarca?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
         marcafacade.edit(marca);
        marca = new Marca();
        return "ListaMarcaes";
    }

   
    
}
