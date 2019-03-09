/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Bodegainsumo;
import facades.BodegainsumoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "bodegainsumoControlador")
@SessionScoped
public class BodegaInsumoControlador implements Serializable {

    @EJB
    BodegainsumoFacade bodegainsumoFacade;
    private Bodegainsumo bodega;
    private Bodegainsumo bodegaSeleccionado;
    private boolean showpopup;

    public BodegaInsumoControlador() {

        bodega = new Bodegainsumo();
    }

    public Bodegainsumo getBodega() {
        return bodega;
    }

    public void setBodega(Bodegainsumo bodega) {
        this.bodega = bodega;
    }

    public Bodegainsumo getBodegaSeleccionado() {
        return bodegaSeleccionado;
    }

    public void setBodegaSeleccionado(Bodegainsumo bodegaSeleccionado) {
        this.bodegaSeleccionado = bodegaSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    //Inicio de los metodos crud
    public List<Bodegainsumo> listabodega() {
        return bodegainsumoFacade.findAll();
    }

    public void Consultauno(Bodegainsumo in) {
        showpopup = true;
        bodega = in;
        int id = bodega.getIdbodegainsumo();
        bodegaSeleccionado = bodegainsumoFacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

}

