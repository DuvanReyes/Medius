/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Cotizacion;
import entidades.Detallecotizacion;
import entidades.Insumo;
import entidades.ayudantes.DetalleCotizacionAyudente;
import facades.DetallecotizacionFacade;
import facades.InsumoFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Arco02
 */
@Named(value = "detalleCotizacionControlador")
@ViewScoped
public class DetalleCotizacionControlador implements Serializable {

    @EJB
    DetallecotizacionFacade detallecotizacionfacade;
    @EJB
    InsumoFacade insumofacade;
    private Detallecotizacion detallecotizacion;
    private Cotizacion cotizacion;
    private Insumo insumo;
    private Detallecotizacion detallecotizacionSeleccionado;
    private boolean showpopup;
    String data = "";

    List<DetalleCotizacionAyudente> detallesCotizacionAyudentes;

    public DetalleCotizacionControlador() {
        detallecotizacion = new Detallecotizacion();
        cotizacion = new Cotizacion();
        insumo = new Insumo();
        detallesCotizacionAyudentes = new ArrayList<>();
    }

    public Detallecotizacion getDetallecotizacion() {
        return detallecotizacion;
    }

    public void setDetallecotizacion(Detallecotizacion detallecotizacion) {
        this.detallecotizacion = detallecotizacion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Detallecotizacion getDetallecotizacionSeleccionado() {
        return detallecotizacionSeleccionado;
    }

    public void setDetallecotizacionSeleccionado(Detallecotizacion detallecotizacionSeleccionado) {
        this.detallecotizacionSeleccionado = detallecotizacionSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    public List<DetalleCotizacionAyudente> getDetallesCotizacionAyudentes() {
        return detallesCotizacionAyudentes;
    }

    public void setDetallesCotizacionAyudentes(List<DetalleCotizacionAyudente> detallesCotizacionAyudentes) {
        this.detallesCotizacionAyudentes = detallesCotizacionAyudentes;
    }

    //Inicio de los metodos crud
    public List<DetalleCotizacionAyudente> listaInsumo() {
        List<Insumo> insumos = insumofacade.findAll();
        DetalleCotizacionAyudente dca;

        for (Insumo insumo1 : insumos) {
            dca = new DetalleCotizacionAyudente();

            dca.setInsumoId(insumo1.getIdinsumo());
            dca.setNombre(insumo1.getNombreinsumo());
            dca.setCantidad(0);
            dca.setPrecio(0d);

            detallesCotizacionAyudentes.add(dca);
        }

        return detallesCotizacionAyudentes;
    }

    public void Consultauno(Detallecotizacion in) {
        showpopup = true;
        detallecotizacion = in;
        int id = detallecotizacion.getIddetallecotizacion();
        detallecotizacionSeleccionado = detallecotizacionfacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Inicio de los metodos crud
    public List<Detallecotizacion> listadetallecotizacion() {
        return detallecotizacionfacade.findAll();
    }

    //Registro de un dato
    public String registrar() {
        for (DetalleCotizacionAyudente dca : detallesCotizacionAyudentes) {
            if (dca.getCantidad() > 0 && dca.getPrecio() > 0) {
                detallecotizacion = new Detallecotizacion();
                detallecotizacion.setInsumoid(new Insumo(dca.getInsumoId()));
                detallecotizacion.setCotizacionid(cotizacion);
                detallecotizacion.setCantida(dca.getCantidad());
                detallecotizacion.setPrecio(dca.getPrecio());

                detallecotizacionfacade.create(detallecotizacion);
            }
        }

        return "ListaCotizaciones?faces-redirect=true";

    }

    //eliminar de un dato
    public String eliminar(Detallecotizacion c) {
        detallecotizacion = c;
        detallecotizacionfacade.remove(detallecotizacion);
        detallecotizacion = new Detallecotizacion();
        return "ListaDetallecotizaciones?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Detallecotizacion c) {
        detallecotizacion = c;
        return "ModificaDetallecotizacion?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        detallecotizacionfacade.edit(detallecotizacion);
        detallecotizacion = new Detallecotizacion();
        return "ListaDetallecotizaciones";
    }

    public String datosGrafica() {
        data = "";
        for (Detallecotizacion detcot : detallecotizacionfacade.findAll()) {
            data = data + "{y: " + detcot.getCantida() + ", name:\"" + detcot.getInsumoid().getNombreinsumo() + "\", exploded: true},";
        }

        return data;
    }

}
