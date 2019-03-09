/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Compra;
import entidades.Cotizacion;
import entidades.ayudantes.DetalleCotizacionAyudente;
import facades.CompraFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "comprasControlador")
@SessionScoped
public class ComprasControlador implements Serializable {

    @EJB
    CompraFacade comprafacade;
    private Compra compra;
    private Cotizacion cotizacion;
    private Compra compraSeleccionado;
    private boolean showpopup;
    private List<DetalleCotizacionAyudente> detalleCotizacionAyudentes;

    public ComprasControlador() {
        compra = new Compra();
        cotizacion = new Cotizacion();
        detalleCotizacionAyudentes = new ArrayList<>();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Compra getCompraSeleccionado() {
        return compraSeleccionado;
    }

    public void setCotizacionSeleccionado(Compra compraSeleccionado) {
        this.compraSeleccionado = compraSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    @PostConstruct
    public void init() {
        List<Compra> listaCompras = comprafacade.findAll();
        if (listaCompras.size() > 0) {
            compra.setIdcompra(listaCompras.get(listaCompras.size() - 1).getIdcompra());
        }
    }

    //Inicio de los metodos crud
    public List<Compra> listacompra() {
        List<Compra> listaCompras = comprafacade.findAll();
        return listaCompras;
    }

    public void Consultauno(Compra in) {
        showpopup = true;
        compra = in;
        int id = compra.getIdcompra();
        compraSeleccionado = comprafacade.find(id);
        detalleCotizacionAyudentes = comprafacade.detallesCotizacion(id);
    }

    public List<DetalleCotizacionAyudente> getDetalleCotizacionAyudentes() {
        return detalleCotizacionAyudentes;
    }

    public void setDetalleCotizacionAyudentes(List<DetalleCotizacionAyudente> detalleCotizacionAyudentes) {
        this.detalleCotizacionAyudentes = detalleCotizacionAyudentes;
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public String registrar() {
        compra.setCotizacionid(cotizacion);
        comprafacade.create(compra);
        compra = new Compra();
        return "DetalleCompra?faces-redirect=true";

    }

    //eliminar de un dato
    public String eliminar(Compra c) {
        compra = c;
        comprafacade.remove(compra);
        compra = new Compra();
        return "ListaCompraes?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Compra c) {
        compra = c;
        return "ModificaCompra?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        comprafacade.edit(compra);
        compra = new Compra();
        return "ListaCompraes";
    }

}
