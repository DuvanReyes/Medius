/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Bodega;
import entidades.Detallemovimiento;
import entidades.Insumo;
import entidades.Movimientoinventario;
import entidades.ayudantes.DetalleCotizacionAyudente;
import facades.DetallemovimientoFacade;
import facades.InsumoFacade;
import facades.MovimientoinventarioFacade;
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
@Named(value = "documentoInvetarioControlador")
@SessionScoped
public class DocumentoInvetarioControlador implements Serializable {

    @EJB
    MovimientoinventarioFacade movimientoinventariofacade;
    @EJB
    InsumoFacade insumoFacade;
    @EJB
    DetallemovimientoFacade detalleMovimientoFacade;
    private Movimientoinventario movimientoinventario;
    private Bodega bodega1;
    private Bodega bodega2;
    private Movimientoinventario documentoInvetarioSeleccionado;
    private boolean showpopup;
    List<DetalleCotizacionAyudente> detallesCotizacionAyudentes;

    public DocumentoInvetarioControlador() {
        movimientoinventario = new Movimientoinventario();
        bodega1 = new Bodega();
        bodega2 = new Bodega();

        detallesCotizacionAyudentes = new ArrayList<>();
    }

    public Movimientoinventario getMovimientoinventario() {
        return movimientoinventario;
    }

    public void setMovimientoinventario(Movimientoinventario movimientoinventario) {
        this.movimientoinventario = movimientoinventario;
    }

    public Bodega getBodega1() {
        return bodega1;
    }

    public void setBodega1(Bodega bodega1) {
        this.bodega1 = bodega1;
    }

    public Bodega getBodega2() {
        return bodega2;
    }

    public void setBodega2(Bodega bodega2) {
        this.bodega2 = bodega2;
    }

    public Movimientoinventario getdocumentoInvetarioSeleccionado() {
        return documentoInvetarioSeleccionado;
    }

    public void setdocumentoInvetarioSeleccionado(Movimientoinventario documentoInvetarioSeleccionado) {
        this.documentoInvetarioSeleccionado = documentoInvetarioSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    public List<DetalleCotizacionAyudente> listaInsumo() {
        detallesCotizacionAyudentes.clear();
        List<Insumo> insumos = insumoFacade.findAll();
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

    //Inicio de los metodos crud
    public List<Movimientoinventario> listamovimientoinventario() {
        return movimientoinventariofacade.findAll();
    }

    public void Consultauno(Movimientoinventario in) {
        showpopup = true;
        movimientoinventario = in;
        int id = movimientoinventario.getIdmovimiento();
        documentoInvetarioSeleccionado = movimientoinventariofacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public String registrar() {

        if (movimientoinventario.getFuente().equals("ini")) {
            
            movimientoinventario.setBodegaid(new Bodega(bodega1.getIdbodega()));
            movimientoinventario.setBodegadestinoid(new Bodega(bodega2.getIdbodega()));
            
            movimientoinventariofacade.crearMovimientoInventario(movimientoinventario, detallesCotizacionAyudentes, "ini");
        } else if (movimientoinventario.getFuente().equals("tb")) {
            movimientoinventario.setBodegaid(new Bodega(bodega1.getIdbodega()));
            movimientoinventario.setBodegadestinoid(new Bodega(bodega2.getIdbodega()));
            
            movimientoinventariofacade.crearMovimientoInventario(movimientoinventario, detallesCotizacionAyudentes, "tb");
        } else if (movimientoinventario.getFuente().equals("sb")){
            movimientoinventario.setBodegaid(new Bodega(bodega1.getIdbodega()));
            movimientoinventario.setBodegadestinoid(new Bodega(bodega2.getIdbodega()));
            
            movimientoinventariofacade.crearMovimientoInventario(movimientoinventario, detallesCotizacionAyudentes, "sb");
        } else if ((movimientoinventario.getFuente().equals("pf"))){
            movimientoinventario.setBodegaid(new Bodega(bodega1.getIdbodega()));
            movimientoinventario.setBodegadestinoid(new Bodega(bodega2.getIdbodega()));
            
            movimientoinventariofacade.crearMovimientoInventario(movimientoinventario, detallesCotizacionAyudentes, "pf");
        } else if ((movimientoinventario.getFuente().equals("sc"))){
            movimientoinventario.setBodegaid(new Bodega(bodega1.getIdbodega()));
            movimientoinventario.setBodegadestinoid(new Bodega(bodega2.getIdbodega()));
            
            movimientoinventariofacade.crearMovimientoInventario(movimientoinventario, detallesCotizacionAyudentes, "sc");
        }

        movimientoinventario = new Movimientoinventario();
        return "RegistraMovimientoInventario?faces-redirect=true";

    }
    
    @PostConstruct
    public void init(){
        movimientoinventario.setFuente("ini");
    }

    //eliminar de un dato
    public String eliminar(Movimientoinventario c) {
        movimientoinventario = c;
        movimientoinventariofacade.remove(movimientoinventario);
        movimientoinventario = new Movimientoinventario();
        return "ListaDocumentosInventario?faces-redirect=true";
    }

    //Carga del dato selecionado
    public String preEditar(Movimientoinventario c) {
        movimientoinventario = c;
        return "ModificaMovivmientoInventario?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        movimientoinventariofacade.edit(movimientoinventario);
        movimientoinventario = new Movimientoinventario();
        return "ListaDocumentosInventario?faces-redirect=true";
    }

}
