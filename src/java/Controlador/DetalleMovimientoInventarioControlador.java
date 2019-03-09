/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Detallemovimiento;
import entidades.Insumo;
import entidades.Movimientoinventario;
import facades.DetallemovimientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "detalleMovimientoInventario")
@SessionScoped
public class DetalleMovimientoInventarioControlador implements Serializable {

    @EJB 
    DetallemovimientoFacade detallemovimientofacade;
    private Detallemovimiento detallemovimiento;
    private Movimientoinventario movimientoinventario;
    private Insumo insumo;
    
    public DetalleMovimientoInventarioControlador() {
        detallemovimiento= new Detallemovimiento();
        movimientoinventario= new Movimientoinventario();
        insumo= new Insumo();
    }

    public Detallemovimiento getDetallemovimiento() {
        return detallemovimiento;
    }

    public void setDetallemovimiento(Detallemovimiento detallemovimiento) {
        this.detallemovimiento = detallemovimiento;
    }

    public Movimientoinventario getMovimientoinventario() {
        return movimientoinventario;
    }

    public void setMovimientoinventario(Movimientoinventario movimientoinventario) {
        this.movimientoinventario = movimientoinventario;
    }
    
    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    

  //Inicio de los metodos crud
     public List<Detallemovimiento> listadetallemovimiento(){
        return detallemovimientofacade.findAll();
    }
          
    //Registro de un dato
    public String registrar(){
        detallemovimiento.setInsumoid(insumo);
        detallemovimiento.setMovimientoid(movimientoinventario);
        detallemovimientofacade.create(detallemovimiento);
        detallemovimiento = new Detallemovimiento();       
        return "ListaDetalleMovimiento?faces-redirect=true";  
      
    }
    
    
    
    //eliminar de un dato
    public String eliminar(Detallemovimiento c){
        detallemovimiento = c;
        detallemovimientofacade.remove(detallemovimiento);
        detallemovimiento = new Detallemovimiento();
        return "ListaDetalleMovimiento?faces-redirect=true";
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Detallemovimiento c){
        detallemovimiento=c;
        return "ModificaDetallemovimiento?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
         detallemovimientofacade.edit(detallemovimiento);
        detallemovimiento = new Detallemovimiento();
        return "ListaDetalleMovimiento?faces-redirect=true";
    }

}
