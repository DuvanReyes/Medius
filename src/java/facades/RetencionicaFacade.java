/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Retencionica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Arco02
 */
@Stateless
public class RetencionicaFacade extends AbstractFacade<Retencionica> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RetencionicaFacade() {
        super(Retencionica.class);
    }
    
}
