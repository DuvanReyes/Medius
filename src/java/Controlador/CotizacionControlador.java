/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Cotizacion;
import entidades.Proveedor;
import entidades.ayudantes.DetalleCotizacionAyudente;
import facades.CompraFacade;
import facades.CotizacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "cotizacionControlador")
@SessionScoped
public class CotizacionControlador implements Serializable {

    @EJB
    CotizacionFacade cotizacionfacade;
    @EJB
    CompraFacade comprafacade;
    private Cotizacion cotizacion;
    private Proveedor proveedor;
    private Cotizacion cotizacionSeleccionado;
    private boolean showpopup;
    private List<DetalleCotizacionAyudente> detalleCotizacionAyudentes;

    public CotizacionControlador() {
        cotizacion = new Cotizacion();
        proveedor = new Proveedor();
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Cotizacion getCotizacionSeleccionado() {
        return cotizacionSeleccionado;
    }

    public void setCotizacionSeleccionado(Cotizacion cotizacionSeleccionado) {
        this.cotizacionSeleccionado = cotizacionSeleccionado;
    }

    public CotizacionFacade getCotizacionfacade() {
        return cotizacionfacade;
    }

    public void setCotizacionfacade(CotizacionFacade cotizacionfacade) {
        this.cotizacionfacade = cotizacionfacade;
    }

    public List<DetalleCotizacionAyudente> getDetalleCotizacionAyudentes() {
        return detalleCotizacionAyudentes;
    }

    public void setDetalleCotizacionAyudentes(List<DetalleCotizacionAyudente> detalleCotizacionAyudentes) {
        this.detalleCotizacionAyudentes = detalleCotizacionAyudentes;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    //Inicio de los metodos crud
    public List<Cotizacion> listacotizacion() {
        List<Integer> idsUsados = cotizacionfacade.obtenerIdsCotizaciones();
        List<Cotizacion> cotizaciones = cotizacionfacade.findAll();
        List<Cotizacion> disponibles = new ArrayList<>();

        for (Cotizacion cotizacion : cotizaciones) {
            if (!idsUsados.contains(cotizacion.getIdcotizacion())) {
                disponibles.add(cotizacion);
            }
        }

        return disponibles;
    }

    public void Consultauno(Cotizacion in) {
        showpopup = true;
        cotizacion = in;
        int id = cotizacion.getIdcotizacion();
        cotizacionSeleccionado = cotizacionfacade.find(id);
        System.out.println("COTIZACION ID: " + id);
        detalleCotizacionAyudentes = comprafacade.detallesCotizacion(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public String registrar() {
        cotizacion.setProveedorid(proveedor);
        cotizacionfacade.create(cotizacion);
        cotizacion = new Cotizacion();
        return "DetalleCotizacion?faces-redirect=true";

    }

    //eliminar de un dato
    public String eliminar(Cotizacion c) {
        cotizacion = c;
        cotizacionfacade.remove(cotizacion);
        cotizacion = new Cotizacion();
        return "ListaCotizaciones?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Cotizacion c) {
        cotizacion = c;
        return "ModificaCotizacion?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        cotizacionfacade.edit(cotizacion);
        cotizacion = new Cotizacion();
        return "ListaCotizaciones?faces-redirect=true";
    }

}
