package entidades.ayudantes;

/**
 *
 * @author John Ortiz Ordoñez
 */
public class DetalleCotizacionAyudente {
    private Integer cotizacionId;
    private Integer insumoId;
    private String nombre;
    private Double precio;
    private Integer cantidad;

    public DetalleCotizacionAyudente() {
    }

    public DetalleCotizacionAyudente(Integer cotizacionId, Integer insumoId, String nombre, Double precio, Integer cantidad) {
        this.cotizacionId = cotizacionId;
        this.insumoId = insumoId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Integer getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(Integer cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public Integer getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Integer insumoId) {
        this.insumoId = insumoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
