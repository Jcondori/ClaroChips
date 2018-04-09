package Controller;

import Dao.ModalidadDao;
import Models.Modalidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

@Named(value = "modalidadController")
@SessionScoped
public class ModalidadController implements Serializable {

    List<Modalidad> LstModalidadCombo = new ArrayList();
    
    @PostConstruct
    public void start(){
        try {
            listarComboBox();
        } catch (Exception ex) {
            Logger.getLogger(ModalidadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarComboBox() throws Exception{
        ModalidadDao dao;
        try {
            dao = new ModalidadDao();
            LstModalidadCombo = dao.listarComboBox();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Getter and Setter
     * @return 
     */
    public List<Modalidad> getLstModalidadCombo() {
        return LstModalidadCombo;
    }

    public void setLstModalidadCombo(List<Modalidad> LstModalidadCombo) {
        this.LstModalidadCombo = LstModalidadCombo;
    }
    
}
