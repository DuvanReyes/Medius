/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Insumo;
import entidades.Marca;
import entidades.Reteiva;
import facades.InsumoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arco02
 */
@Named(value = "insumoControlador")
@SessionScoped
public class InsumoControlador implements Serializable {

    @EJB
    InsumoFacade insumofacade;
    private Insumo insumo;
    private Insumo insumoSeleccionado;
    private Marca marca;
    private Reteiva reteiva;
    private String data = "";
    private boolean showpopup;

    public InsumoControlador() {
        insumo = new Insumo();
        marca = new Marca();
        reteiva = new Reteiva();
        insumoSeleccionado = new Insumo();

    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Reteiva getReteiva() {
        return reteiva;
    }

    public void setReteiva(Reteiva reteiva) {
        this.reteiva = reteiva;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    //Inicio de los metodos crud
    public List<Insumo> listaInsumo() {
        return insumofacade.findAll();
    }

    public void Consultauno(Insumo in) {
        showpopup = true;
        insumo = in;
        int id = insumo.getIdinsumo();
        insumoSeleccionado = insumofacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public void registrar() {

        try {

            if (insumo.getNombreinsumo() == null || insumo.getNombreinsumo().isEmpty() || insumo.getNombreinsumo().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));

            } else if (insumo.getInsumocantidadminima() <= 0 && insumo.getInsumocantidadmaxima() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor Ingrese los valores correspondientes en los campos de cantidades minimas y maximas"));

            } else if (insumo.getInsumocantidadminima() <= 0 || insumo.getInsumocantidadmaxima() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Hay un campo en las cantidades el cual esta vacio"));

            } else if (insumo.getInsumocantidadminima() == insumo.getInsumocantidadmaxima()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad minima y maxima son iguales"));

            } else if (insumo.getInsumocantidadminima() > insumo.getInsumocantidadmaxima()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad minima no puede ser mayor a la cantidad maxima"));

            } else {
                insumo.setInsumomarcaid(marca);
                insumo.setInsumoreteivaid(reteiva);
                insumofacade.create(insumo);
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                insumo = new Insumo();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }

    }

    //eliminar de un dato
    public void eliminar(Insumo i) {
        try {
            insumo = i;
            insumofacade.remove(insumo);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            insumo = new Insumo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
    }

    //Carga del dato selecionado
    public String preEditar(Insumo i) {
        insumo = i;
        return "ModificaInsumo?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {

        try {
            if (insumo.getNombreinsumo() == null || insumo.getNombreinsumo().isEmpty() || insumo.getNombreinsumo().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));

            } else if (insumo.getInsumocantidadminima() <= 0 && insumo.getInsumocantidadmaxima() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor Ingrese los valores correspondientes en los campos de cantidades minimas y maximas"));

            } else if (insumo.getInsumocantidadminima() <= 0 || insumo.getInsumocantidadmaxima() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Hay un campo en las cantidades el cual esta vacio"));

            } else if (insumo.getInsumocantidadminima() == insumo.getInsumocantidadmaxima()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad minima y maxima son iguales"));

            } else if (insumo.getInsumocantidadminima() > insumo.getInsumocantidadmaxima()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad minima no puede ser mayor a la cantidad maxima"));

            } else {
                insumofacade.edit(insumo);
                insumo = new Insumo();
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                return "ListaInsumos?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento"));
            return null;
        }
        return null;
    }

    public String datosGraficaUno() {
        data = "";
        for (Insumo ins : insumofacade.findAll()) {
            data = data + "{y: " + ins.getInsumocantidadminima() + "," + "  label:\"" + ins.getNombreinsumo() + "\"},";
        }

        return data;
    }

}
