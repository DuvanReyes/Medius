/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Bodega;
import facades.BodegaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "bodegasControlador")
@SessionScoped
public class BodegasControlador implements Serializable {

    @EJB
    BodegaFacade bodegafacade;
    private Bodega bodega;
    private Bodega bodegaSeleccionado;
    private boolean showpopup;

    public BodegasControlador() {

        bodega = new Bodega();
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Bodega getBodegaSeleccionado() {
        return bodegaSeleccionado;
    }

    public void setBodegaSeleccionado(Bodega bodegaSeleccionado) {
        this.bodegaSeleccionado = bodegaSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    //Inicio de los metodos crud
    public List<Bodega> listabodega() {
        return bodegafacade.findAll();
    }

    public void Consultauno(Bodega in) {
        showpopup = true;
        bodega = in;
        int id = bodega.getIdbodega();
        bodegaSeleccionado = bodegafacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public String registrar() {
        bodegafacade.create(bodega);
        bodega = new Bodega();
        return "RegistroBodegas?faces-redirect=true";

    }

    //eliminar de un dato
    public String eliminar(Bodega c) {
        bodega = c;
        bodegafacade.remove(bodega);
        bodega = new Bodega();
        return "ListaBodegas?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Bodega c) {
        bodega = c;
        return "ModificaBodega?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        bodegafacade.edit(bodega);
        bodega = new Bodega();
        return "ListaBodegas?faces-redirect=true";
    }

}

