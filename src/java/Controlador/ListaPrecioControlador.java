/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Listaprecio;
import facades.ListaprecioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "listaPrecioControlador")
@SessionScoped
public class ListaPrecioControlador implements Serializable {

    @EJB
    private ListaprecioFacade listaPreciofacade ;
    private Listaprecio listaprecio;
    
    public ListaPrecioControlador() {
        listaprecio= new Listaprecio();
    }

       public Listaprecio getListaprecio() {
        return listaprecio;
    }

    public void setListaprecio(Listaprecio listaprecio) {
        this.listaprecio = listaprecio;
    }
    
     //Inicio de los metodos crud
     public List<Listaprecio> listalistaprecio(){
        return listaPreciofacade.findAll();
    }
          
    //Registro de un dato
    public String registrar(){
        listaPreciofacade.create(listaprecio);
        listaprecio = new Listaprecio();       
        return "RegistraListaPrecios?faces-redirect=true";  
      
    }
    
    
    
    //eliminar de un dato
    public String eliminar(Listaprecio l){
        listaprecio = l;
        listaPreciofacade.remove(listaprecio);
        listaprecio = new Listaprecio();
        return "ListadePrecios?faces-redirect=true";
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Listaprecio l){
        listaprecio=l;
        return "ModificaListaPrecio?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
         listaPreciofacade.edit(listaprecio);
        listaprecio = new Listaprecio();
        return "ListadePrecios?faces-redirect=true";
    }

 

   
    
    
}
