/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Contratoempleado;
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
public class ContratoempleadoFacade extends AbstractFacade<Contratoempleado> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoempleadoFacade() {
        super(Contratoempleado.class);
    }
    
     public double buscaSueldoCargo(int dato) {
        double codigo = 0;
        try {
            List listaCargo = new ArrayList<>();
            Query query = em.createQuery("SELECT c.salariominimo FROM Cargo c WHERE c.idcargo = :var1");
            query.setParameter("var1", dato);
            listaCargo = query.getResultList();
            if (listaCargo.isEmpty()) {
                return codigo;
            } else {
                codigo = (double) listaCargo.get(0);
            }
        } catch (Exception e) {
        }
         return codigo;
    }
    
}
