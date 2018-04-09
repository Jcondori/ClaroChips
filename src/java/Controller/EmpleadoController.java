package Controller;

import Dao.EmpleadoDao;
import Models.Empleado;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController implements Serializable {

    List<Empleado> LstEmpleado = new ArrayList();
    Empleado empleado = new Empleado();

    //Variables de Usuario
    private String user, pass;
    Empleado Usuario = new Empleado();

    @PostConstruct
    public void start() {
        try {
            listEmpleado();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listEmpleado() throws Exception {
        EmpleadoDao dao;
        try {
            dao = new EmpleadoDao();
            LstEmpleado = dao.listEmpleado();
        } catch (Exception e) {
            throw e;
        }
    }

    public void insert() throws Exception {
        EmpleadoDao dao;
        try {
            dao = new EmpleadoDao();
            dao.insert(empleado);
            listEmpleado();
            clean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void update() throws Exception {
        EmpleadoDao dao;
        try {
            dao = new EmpleadoDao();
            dao.update(empleado);
            listEmpleado();
            clean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void updatePassword() throws Exception {
        EmpleadoDao dao;
        try {
            dao = new EmpleadoDao();
            dao.cambiarPassword(empleado.getCodigo(),empleado.getPassword());
            listEmpleado();
            clean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña Actualizada"));
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Control de Usuario
    public void startSession() throws Exception {
        EmpleadoDao dao;
        try {
            dao = new EmpleadoDao();
            Usuario = dao.startSession(user, pass);
            if (Usuario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", Usuario);
                switch (Integer.valueOf(Usuario.getPrivilegio())) {
                    case 1:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Template/Dashboard.xhtml");
                        break;
                    case 2:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Search/Search.xhtml");
                        break;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario/Contraseña Inconrrecto"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void securitySession() throws IOException {
        Empleado us = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Claro");
        }
    }

    public void securityLogin() throws IOException {
        Empleado us = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Search/Search.xhtml");
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Claro");
    }

    public void clean() {
        empleado = new Empleado();
    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public List<Empleado> getLstEmpleado() {
        return LstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> LstEmpleado) {
        this.LstEmpleado = LstEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

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

    public Empleado getUsuario() {
        return Usuario;
    }

    public void setUsuario(Empleado Usuario) {
        this.Usuario = Usuario;
    }

}
