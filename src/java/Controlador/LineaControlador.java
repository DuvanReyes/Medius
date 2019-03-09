/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Linea;
import facades.LineaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "lineaControlador")
@SessionScoped
public class LineaControlador implements Serializable {

    @EJB 
    LineaFacade lineafacade;
    private Linea linea;
    
    public LineaControlador() {
        linea = new Linea();
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    
     //Inicio de los metodos crud
     public List<Linea> listalinea(){
        return lineafacade.findAll();
    }
          
    //Registro de un dato
    public String registrar(){
        lineafacade.create(linea);
        linea = new Linea();       
        return "RegistraLinea?faces-redirect=true";  
      
    }
    
    //eliminar de un dato
    public String eliminar(Linea l){
        linea = l;
        lineafacade.remove(linea);
        linea = new Linea();
        return "ListaLineaes?faces-redirect=true";
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Linea l){
        linea=l;
        return "ModificaLinea?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
         lineafacade.edit(linea);
        linea = new Linea();
        return "ListaLineaes";
    }

   
    
    
}
