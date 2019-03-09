/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Cargo;
import entidades.Ciudad;
import entidades.Contratoempleado;
import entidades.Empleado;
import entidades.Entidadseguridad;
import entidades.Seccion;
import facades.CargoFacade;
import facades.ContratoempleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arco02
 */
@Named(value = "contratoControlador")
@SessionScoped
public class ContratoControlador implements Serializable {

    @EJB
    ContratoempleadoFacade contratoempleadoFacade;
    private Contratoempleado contratoempleado;
    private Empleado empleado;
    private Ciudad ciudad;
    private Cargo cargo;
    private Seccion seccion;
    private Entidadseguridad Entidadeps;
    private Entidadseguridad Entidadarl;
    private Entidadseguridad Entidadcaja;
    private Entidadseguridad Entidadpension;
     private Entidadseguridad Entidadcesantias; 
     private CargoFacade cargoFacade;
   
    
    public ContratoControlador() {
        contratoempleado = new Contratoempleado();
        empleado = new Empleado();
        ciudad = new Ciudad();
        cargo =  new Cargo();
        seccion = new Seccion();
        Entidadeps= new Entidadseguridad();
        Entidadarl= new Entidadseguridad();
        Entidadcaja= new Entidadseguridad();
        Entidadpension= new Entidadseguridad();
        Entidadcesantias= new Entidadseguridad();
    }

    public Contratoempleado getContratoempleado() {
        return contratoempleado;
    }

    public void setContratoempleado(Contratoempleado contratoempleado) {
        this.contratoempleado = contratoempleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Entidadseguridad getEntidadeps() {
        return Entidadeps;
    }

    public void setEntidadeps(Entidadseguridad Entidadeps) {
        this.Entidadeps = Entidadeps;
    }

    public Entidadseguridad getEntidadarl() {
        return Entidadarl;
    }

    public void setEntidadarl(Entidadseguridad Entidadarl) {
        this.Entidadarl = Entidadarl;
    }

    public Entidadseguridad getEntidadcaja() {
        return Entidadcaja;
    }

    public void setEntidadcaja(Entidadseguridad Entidadcaja) {
        this.Entidadcaja = Entidadcaja;
    }

    public Entidadseguridad getEntidadpension() {
        return Entidadpension;
    }

    public void setEntidadpension(Entidadseguridad Entidadpension) {
        this.Entidadpension = Entidadpension;
    }

    public Entidadseguridad getEntidadcesantias() {
        return Entidadcesantias;
    }

    public void setEntidadcesantias(Entidadseguridad Entidadcesantias) {
        this.Entidadcesantias = Entidadcesantias;
    }
    
   //Inicio de los metodos crud
     public List<Contratoempleado> listaContratoempleado(){
        return contratoempleadoFacade.findAll();
    }
     
     public void buscasueldo(){
     String valor = String.valueOf(cargo.getIdcargo());
         int id = Integer.parseInt(valor);
         double recibeSueldo=contratoempleadoFacade.buscaSueldoCargo(id);
        contratoempleado.setSalarioinicial(recibeSueldo);
     
     }
          
    //Registro de un dato
    public String registrar(){
        
        try {
            if(contratoempleado.getPeriodoprueba()<=0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese los días de prueba"));  
            }else if(contratoempleado.getFechainicio().equals(null) || contratoempleado.getFechainicio()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos de fechas son obligatorios")); 
            }else if(contratoempleado.getFechafin().equals(null) || contratoempleado.getFechafin()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos de fechas son obligatorios")); 
            }else if(contratoempleado. getFechafin().before(contratoempleado.getFechainicio())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La fecha fin no puede ser menor a la fecha inicio")); 
            }else if(contratoempleado.getFechainicio().equals(contratoempleado.getFechafin())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "No se puede iniciar un contrato y terminarlo el mismo día")); 
            }else if(contratoempleado.getSalarioactual()<contratoempleado.getSalarioinicial()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El salario actual no puede ser menor al inicial")); 
            }else if(contratoempleado.getSalarioinicial()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario inicial")); 
            }else if(contratoempleado.getSalarioactual()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario actual")); 
            }
            else{
        contratoempleado.setEmpleadoid(empleado);
        contratoempleado.setCiudadtrabajo(ciudad);
        contratoempleado.setCodigocargo(cargo);
        contratoempleado.setCodigoseccion(seccion);
        contratoempleado.setCodigoeps(Entidadeps);
        contratoempleado.setCodigoarl(Entidadarl);
        contratoempleado.setCodigopensiones(Entidadpension);
        contratoempleado.setCodigocesantias(Entidadcesantias);
        contratoempleado.setCodigocaja(Entidadcaja);
        contratoempleadoFacade.create(contratoempleado);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        contratoempleado = new Contratoempleado();
        return "RegistraContrato?faces-redirect=true";
        }
        } catch (Exception e) { 
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Los campos fechas son obligatorios"));
        }
        
       return null;

    }
    
    //eliminar de un dato
    public void eliminar(Contratoempleado i){
        try {
        contratoempleado = i;
        contratoempleadoFacade.remove(i);
        contratoempleado = new Contratoempleado();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Contratoempleado i){
        contratoempleado=i;
        return "ModificaContrato?faces-redirect=true";
    }
    
   
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
           if(contratoempleado.getPeriodoprueba()<=0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese los días de prueba"));  
            }else if(contratoempleado.getFechainicio().equals(null) || contratoempleado.getFechainicio()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos de fechas son obligatorios")); 
            }else if(contratoempleado.getFechafin().equals(null) || contratoempleado.getFechafin()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos de fechas son obligatorios")); 
            }else if(contratoempleado. getFechafin().before(contratoempleado.getFechainicio())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La fecha fin no puede ser menor a la fecha inicio")); 
            }else if(contratoempleado.getSalarioactual()<contratoempleado.getSalarioinicial()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El salario actual no puede ser menor al inicial")); 
            }else if(contratoempleado.getSalarioinicial()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario inicial")); 
            }else if(contratoempleado.getSalarioactual()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario actual")); 
            }
            else{
         contratoempleadoFacade.edit(contratoempleado);
        contratoempleado = new Contratoempleado();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaContrato?faces-redirect=true";
            }  
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
             return null;
        }
     return null;
    }  
    
    
}
