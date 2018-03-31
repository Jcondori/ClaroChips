package Controller;

import Dao.EmpleadoDao;
import Models.Empleado;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

@Named(value = "empleadoController")
@SessionScoped
public class EmpleadoController implements Serializable {
    
    List<Empleado> LstEmpleado = new ArrayList();
    Empleado empleado = new Empleado();
    
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
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void clean(){
        empleado = new Empleado();
    }
    
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
    
}
