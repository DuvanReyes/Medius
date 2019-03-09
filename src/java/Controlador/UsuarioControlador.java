/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Tercero;
import entidades.Usuario;
import facades.UsuarioFacade;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Mailer;

/**
 *
 * @author Arco02 Medius v3
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    @EJB
    UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private Tercero tercero;
    private Usuario usuariologin;
    private String correoUsuario;
    private boolean showpopup;

    public UsuarioControlador() {
        usuario = new Usuario();
        tercero = new Tercero();
        usuariologin = new Usuario();
        usuarioSeleccionado = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public Usuario getUsuariologin() {
        return usuariologin;
    }

    public void setUsuariologin(Usuario usuariologin) {
        this.usuariologin = usuariologin;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void Consultauno(Usuario in) {
        showpopup = true;
        usuario = in;
        int id = usuario.getIdusuario();
        usuarioSeleccionado = usuarioFacade.find(id);
    }

    public void cerrar() {
        showpopup = false;
    }

    //Inicio de los metodos crud
    public List<Usuario> listaUsuario() {
        return usuarioFacade.findAll();
    }

    //Registro de un dato
    public void registrar() {

        try {

            if (usuario.getNombreusuario().equals(null) || usuario.getNombreusuario().isEmpty() || usuario.getNombreusuario() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
            } else if (usuario.getContrasenausuario().isEmpty() || usuario.getContrasenausuario().equals(null) || usuario.getNombreusuario() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese la contraseña"));
            } else if (correoUsuario.isEmpty() || correoUsuario.equals(null) || correoUsuario == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El correo es obligatorio"));
            } else {
                usuario.setTercerousuario(tercero);
                usuarioFacade.create(usuario);
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso. Por favor Revise su correo");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                Mailer.sendBienvenido(getCorreoUsuario(), usuario.getNombreusuario(), usuario.getContrasenausuario());
                usuario = new Usuario();
            }
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
    }

    public void eliminar(Usuario u) {
        try {
            usuario = u;
            usuarioFacade.remove(usuario);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            usuario = new Usuario();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
    }

    //Carga del dato selecionado
    public String preEditar(Usuario u) {
        usuario = u;
        return "ModificaUsuario?faces-redirect=true";
    }

    //metodo para registrar el nuevo dato
    public String editar() {
        try {
            if (usuario.getNombreusuario().equals(null) || usuario.getNombreusuario().isEmpty() || usuario.getNombreusuario() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
            } else {
                usuarioFacade.edit(usuario);
                usuario = new Usuario();
                FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
                FacesContext.getCurrentInstance().addMessage("", mensajeOK);
                return "ListaUsuarios?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento"));
            return null;
        }

        return null;
    }

//metodos de session.
    //verificar redireccion session
    public String validarLogin() {
        Usuario usuarioLogueado;
        String enlacePagina = null;
        usuarioLogueado = usuarioFacade.consultaUsuario(usuario);
        if (usuarioLogueado.getNombreusuario() != null && usuarioLogueado.getContrasenausuario() != null && usuarioLogueado.getEstadousuario() == 1) {
            //Almacenar en la sesion de jsf
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioSesion", usuarioLogueado);
            enlacePagina = "faces/Paginas/Principal/Principal.xhtml?faces-redirect=true";
        } else {
            enlacePagina = "index.xhtml?faces-redirect=true";
        }

        return enlacePagina;
    }

    //verificar si hay una session session.
    public void verificarSesion() {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) contex.getExternalContext().getSessionMap().get("usuarioSesion");
            if (us == null) {
                contex.getExternalContext().redirect("../../index.xhtml?faces-redirect=true");

            }
        } catch (IOException e) {
            // log guardar registro de un error o algo asi
        }
    }

    //cerrar session
    public String cerrarSesion() throws IOException {
        String error = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "faces/../index.xhtml?faces-redirect=true";
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("../../index.xhtml?faces-redirect=true");
        } catch (Exception e) {
            error = "Me voy al carajo, no funciona esta redireccion";
        }
        return error;

    }

    //datos de usuario que esta logueado
    public String userLogueado() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSesion");
        return us.getNombreusuario();
    }

}
