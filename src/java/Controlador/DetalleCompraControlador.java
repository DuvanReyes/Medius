/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Bodega;
import entidades.Compra;
import entidades.Detallecompra;
import entidades.Insumo;
import entidades.ayudantes.DetalleCotizacionAyudente;
import facades.CompraFacade;
import facades.CotizacionFacade;
import facades.DetallecompraFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "detalleCompraControlador")
@SessionScoped
public class DetalleCompraControlador implements Serializable {

    @EJB
    DetallecompraFacade detallecomprafacade;
    @EJB
    CotizacionFacade cotizacionFacade;
    @EJB
    CompraFacade compraFacade;
    private Detallecompra detallecompra;
    private Compra compra;
    private Insumo insumo;
    private Bodega bodega;
    private String data = "";
    private String idCompra;
    private List<DetalleCotizacionAyudente> detalles;

    public DetalleCompraControlador() {
        detallecompra = new Detallecompra();
        compra = new Compra();
        insumo = new Insumo();
        bodega = new Bodega();
    }

    public Detallecompra getDetallecompra() {
        return detallecompra;
    }

    public void setDetallecompra(Detallecompra detallecompra) {
        this.detallecompra = detallecompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    //Inicio de los metodos crud
    public List<Detallecompra> listadetallecompra() {
        return detallecomprafacade.findAll();
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public List<DetalleCotizacionAyudente> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacionAyudente> detalles) {
        this.detalles = detalles;
    }

    //Registro de un dato
    public String registrar() {
        int id = compraFacade.obtenerIdCompraPorIdCotizacion(Integer.parseInt(idCompra));
        for (DetalleCotizacionAyudente detalle : detalles) {
            compra.setIdcompra(id);
            detallecompra.setCompraid(compra);
            detallecompra.setBodegaid(bodega);
            detallecompra.setCantidad(detalle.getCantidad());
            detallecompra.setPrecio(detalle.getPrecio());
            detallecompra.setInsumoid(new Insumo(detalle.getInsumoId()));
            detallecomprafacade.create(detallecompra);
            detallecompra = new Detallecompra();
        }
        return "ListaCompras?faces-redirect=true";

    }

    //eliminar de un dato
    public String eliminar(Detallecompra c) {
        detallecompra = c;
        detallecomprafacade.remove(detallecompra);
        detallecompra = new Detallecompra();
        return "ListaDetallecompraes?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Detallecompra c) {
        detallecompra = c;
        return "ModificaDetallecompra?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        detallecomprafacade.edit(detallecompra);
        detallecompra = new Detallecompra();
        return "ListaDetallecompraes";
    }

    public String datosGrafica() {
        data = "";
        for (Detallecompra detcom : detallecomprafacade.findAll()) {
            data = data + "{y: " + detcom.getCantidad() + ", name:\"" + detcom.getInsumoid().getNombreinsumo() + "\", exploded: true},";
        }

        return data;
    }
    
    @PostConstruct
    public void init(){
        idCompra = cotizacionFacade.obtenerIdsCotizaciones().get(cotizacionFacade.obtenerIdsCotizaciones().size() - 1).toString();
        System.out.println("ID COM: " + idCompra);
        detalles = compraFacade.detallesCotizacion(Integer.parseInt(idCompra));
    }
}
