package Controller;

import Dao.UsuarioDao;
import Models.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
    
    private String user, pass;
    Usuario usuario = new Usuario();
    
    public void startSession() throws Exception {
        UsuarioDao dao;
        try {
            dao = new UsuarioDao();
            usuario = dao.startSession(user, pass);
            if (usuario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Search/Search.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario/Contraseña Inconrrecto"));
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void securitySession() throws IOException {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us == null) {            
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ClaroChips/");
        }
    }
    
    public void securityLogin() throws IOException {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us != null) {            
        FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Search/Search.xhtml");
        }
    }
    
    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ClaroChips/");
    }

    /* Getter and Seter */
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
