/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Ciudad;
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
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    
    

        public Ciudad buscaCodigo(Ciudad ciudad) {
        Ciudad codigo = null;
        try {
            List<Ciudad> listaCodigo = new ArrayList<>();
            Query query = em.createQuery("SELECT c FROM Ciudad c WHERE  c.numerociudad = :var1");
            query.setParameter("var1", ciudad.getNumerociudad());
            listaCodigo = query.getResultList();
            if (listaCodigo.isEmpty()) {
                return codigo;
            } else {
                codigo = listaCodigo.get(0);
            }
        } catch (Exception e) {
        }
        return codigo;
    }
        
}
