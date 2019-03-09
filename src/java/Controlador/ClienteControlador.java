/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Ciudad;
import entidades.Cliente;
import entidades.Listaprecio;
import entidades.Tercero;
import facades.ClienteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author acer
 */
@Named(value = "clienteControlador")
@SessionScoped
public class ClienteControlador implements Serializable {

    @EJB 
    ClienteFacade clientefacade;
    private Cliente cliente;
    private Cliente clienteSeleccionado;
    private Tercero tercero;
    private Listaprecio listaprecio;
    private Ciudad ciudad;
    private boolean showpopup;
    
    public ClienteControlador() {
        cliente =new Cliente();
        tercero = new Tercero();
        listaprecio = new Listaprecio();
        ciudad =new Ciudad();
        clienteSeleccionado=new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public Listaprecio getListaprecio() {
        return listaprecio;
    }

    public void setListaprecio(Listaprecio listaprecio) {
        this.listaprecio = listaprecio;
    }
    
    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }
    
    public void Consultauno(Cliente in){
        showpopup=true;
        cliente=in;
        int id=cliente.getIdcliente();
       clienteSeleccionado=clientefacade.find(id);
    }
    public void cerrar(){
        showpopup=false;
    }
    
    //Inicio de los metodos crud
     public List<Cliente> listaCliente(){
        return clientefacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
         cliente.setCiudadcliente(ciudad);
        cliente.setTerceroid(tercero);
        cliente.setListaprecioid(listaprecio);
        clientefacade.create(cliente);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        cliente = new Cliente();       

        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        } 
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Cliente c){
        try {
        cliente = c;
        clientefacade.remove(cliente);
        cliente = new Cliente();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Cliente c){
        cliente=c;
        return "ModificaCliente?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
         clientefacade.edit(cliente);
        cliente = new Cliente();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaClientes?faces-redirect=true"; 
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento"));
             return "ListaClientes?faces-redirect=true";
        }

    }       
}
