/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Ciudad;
import entidades.Empleado;
import entidades.Tercero;
import facades.EmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arco02
 *
 * MEDIUS SOFTWARE V3
 */
@Named(value = "empleadoControlador")
@SessionScoped
public class EmpleadoControlador implements Serializable {

    @EJB
    EmpleadoFacade empleadoFacade;
    private Empleado empleado;
    private Ciudad ciudad;
    private Tercero tercero;

    public EmpleadoControlador() {
        empleado = new Empleado();
        ciudad = new Ciudad();
        tercero = new Tercero();
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

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    //Inicio de los metodos crud
    public List<Empleado> listaempleado() {
        return empleadoFacade.findAll();
    }

    public void registrar() {

        try {

            if (empleado.getFechaexpedicioncedula() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Falta ingresar la fecha de expediciòn"));
            } else if (empleado.getFechanacimientoempleado() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " Falta ingresar la fecha de nacimiento"));
            } else if (empleado.getFechanacimientoempleado() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicial = new Date();
                Date fechaFinal = empleado.getFechanacimientoempleado();
                int dias = (int) ((fechaInicial.getTime() - fechaFinal.getTime()) / 86400000);
                if (dias<=6205 && dias>0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El empleado no puede ser menor de edad"));
                }else if (dias<=0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "verifique la fecha de nacimiento ya  que esta al dia de hoy"));
                }else{
                empleado.setTerceroidempleado(tercero);
                empleado.setCiudadexpedicion(ciudad);
                empleado.setCiudadnacimiento(ciudad);
                empleadoFacade.create(empleado);
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                empleado = new Empleado();
                }
            } 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Por favor intentelo mas tarde"));
        }

    }

    public String preEditar(Empleado e) {
        empleado = e;
        return "ModificaEmpleado?faces-redirect=true";

    }

    public void eliminar(Empleado e) {
        try {
            empleado = e;
            empleadoFacade.remove(empleado);
            empleado = new Empleado();
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception l) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }

    }

    public String editar() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicial = new Date();
            Date fechaFinal = empleado.getFechanacimientoempleado();
            int dias = (int) ((fechaInicial.getTime() - fechaFinal.getTime()) / 86400000);

            if (dias <= 6205) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El empleado no puede ser menor de edad"));
            } else {
                empleado.setTerceroidempleado(tercero);
                empleado.setCiudadexpedicion(ciudad);
                empleado.setCiudadnacimiento(ciudad);
                empleadoFacade.edit(empleado);
                empleado = new Empleado();
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                return "ListaEmpleados?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento"));
            return null;
        }

        return "ListaEmpleados?faces-redirect=true";
    }
}
