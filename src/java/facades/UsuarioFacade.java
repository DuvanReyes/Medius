/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "MediusSoftwareV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    //arreglo llenado ado por consulta que devuelve como resultado el usuario que inicia la session
    public Usuario consultaUsuario(Usuario usuario) {
        Usuario usuarioLogeado = new Usuario();
        try {
            List<Usuario> listaUsuarios = new ArrayList<>();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE  u.nombreusuario = :var1 AND u.contrasenausuario = :var2");
            query.setParameter("var1", usuario.getNombreusuario());
            query.setParameter("var2", usuario.getContrasenausuario());
            listaUsuarios = query.getResultList();
            if (listaUsuarios.isEmpty()) {
                return usuarioLogeado;
            } else {
                usuarioLogeado = listaUsuarios.get(0);
            }
        } catch (Exception e) {
        }
        return usuarioLogeado;
    }

}
