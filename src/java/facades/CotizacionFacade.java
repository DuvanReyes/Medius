/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Cotizacion;
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
public class CotizacionFacade extends AbstractFacade<Cotizacion> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionFacade() {
        super(Cotizacion.class);
    }
    
    public List<Integer> obtenerIdsCotizaciones(){
        List<Integer> idsCotizaciones = new ArrayList<>();
        
        try {
            Query query = em.createNativeQuery("SELECT COTIZACIONID FROM `compra`");
            
            List<Object> ids = query.getResultList();
            
            for (Object id : ids) {
                idsCotizaciones.add(Integer.parseInt(id.toString()));
            }
            
            return idsCotizaciones;
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }
    
    
}
