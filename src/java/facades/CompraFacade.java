/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Compra;
import entidades.ayudantes.DetalleCotizacionAyudente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Arco02
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    
    public List<DetalleCotizacionAyudente> detallesCotizacion(int idCotizacion){
        List<DetalleCotizacionAyudente> detalles = new ArrayList<>();
        
        try {
            Query query = em.createNativeQuery("SELECT d.COTIZACIONID, I.IDINSUMO, I.NOMBREINSUMO, D.CANTIDA, D.PRECIO FROM detallecotizacion D INNER JOIN insumo I ON D.INSUMOID = I.IDINSUMO WHERE D.COTIZACIONID = " + idCotizacion);
            
            List<Object[]> detallesCotizacionesGenericos = query.getResultList();
            DetalleCotizacionAyudente dca;
            
            for(Object[] a : detallesCotizacionesGenericos){
                dca = new DetalleCotizacionAyudente();
                dca.setCotizacionId(Integer.parseInt(a[0].toString()));
                dca.setInsumoId(Integer.parseInt(a[1].toString()));
                dca.setNombre(a[2].toString());
                dca.setCantidad(Integer.parseInt(a[3].toString()));
                dca.setPrecio(Double.parseDouble(a[4].toString()));
                
                detalles.add(dca);
            }
            
            return detalles;
        } catch (NumberFormatException e) {
            
            return new ArrayList<>();
        }
    }
    
    public int obtenerIdCompraPorIdCotizacion(int idCotizacion){
        try {
            Query query = em.createNativeQuery("SELECT IdCompra FROM `compra` WHERE cotizacionid = " + idCotizacion);
            
            List<Object> ids = query.getResultList();
            
            return Integer.parseInt(ids.get(0).toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
