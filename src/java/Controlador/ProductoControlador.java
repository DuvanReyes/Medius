/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Linea;
import entidades.Marca;
import entidades.Producto;
import entidades.Reteiva;
import facades.ProductoFacade;
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
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    @EJB
    ProductoFacade productofacade;
    private Producto producto;
    private Producto productoseleccionado;
    private Marca marca;
    private Reteiva reteiva;
    private Linea linea;
    private boolean showpopup;

    public ProductoControlador() {
        producto = new Producto();
        marca = new Marca();
        reteiva = new Reteiva();
        linea = new Linea();
        productoseleccionado = new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Producto getProductoseleccionado() {
        return productoseleccionado;
    }

    public void setProductoseleccionado(Producto productoseleccionado) {
        this.productoseleccionado = productoseleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    //Inicio de los metodos crud
    public List<Producto> listaProducto() {
        return productofacade.findAll();
    }

    public void Consultauno(Producto in) {
        showpopup = true;
        producto = in;
        int id = producto.getIdproducto();
        productoseleccionado = productofacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Registro de un dato
    public void registrar() {
        try {
            if (producto.getNombreproducto() == null || producto.getNombreproducto().isEmpty() || producto.getNombreproducto().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
            } else {
                producto.setProductomarcaid(marca);
                producto.setProductoreteivaid(reteiva);
                producto.setProductolineaid(linea);
                productofacade.create(producto);
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                producto = new Producto();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }

    }

    //eliminar de un dato
    public void eliminar(Producto p) {
        try {
            producto = p;
            productofacade.remove(producto);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            producto = new Producto();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }

    }

    //Carga del dato selecionado
    public String preEditar(Producto p) {
        producto = p;
        return "ModificaProducto?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        try {

            if (producto.getNombreproducto() == null || producto.getNombreproducto().isEmpty() || producto.getNombreproducto().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
            } else {
                productofacade.edit(producto);
                producto = new Producto();
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                return "ListaProductos?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento"));
            return null;
        }

        return null;
    }

}
