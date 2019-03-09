/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Rol;
import entidades.Rolusuario;
import entidades.Usuario;
import facades.RolusuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Arco02
 */
@Named(value = "rolUsuariocontrolador")
@SessionScoped
public class RolUsuarioControlador implements Serializable {

    @EJB
    RolusuarioFacade rolusuarioFacade ;
    private Rolusuario rolusuario;
    private Usuario usuario;
    private Rol rol;
    
    public RolUsuarioControlador() {
        rolusuario = new Rolusuario();
        usuario = new Usuario();
        rol = new Rol();
    }

    public Rolusuario getRolusuario() {
        return rolusuario;
    }

    public void setRolusuario(Rolusuario rolusuario) {
        this.rolusuario = rolusuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
     //Inicio de los metodos crud
     public List<Rolusuario> listarolusuario(){
        return rolusuarioFacade.findAll();
    }
          
    
    //Consulta del dato selecionado
    public String preConsulta(Usuario u){
        usuario=u;
        return "RegistroUsuarioRol?faces-redirect=true";
    }
    
    //Registro de un dato
    public String registrar(){
        rolusuario.setRolid(rol);
        rolusuario.setUsuarioid(usuario);
        rolusuarioFacade.create(rolusuario);
        rolusuario = new Rolusuario();       
        return "ListaUsuarios?faces-redirect=true";  
    }
    
}
