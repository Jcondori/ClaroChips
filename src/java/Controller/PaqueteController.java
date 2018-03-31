package Controller;

import Dao.ChipsDao;
import Dao.PaqueteDao;
import Models.Paquete;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "paqueteController")
@SessionScoped
public class PaqueteController implements Serializable {

    List<Paquete> LstPaquete = new ArrayList();
    Paquete paquete = new Paquete();

    java.util.Date fecha = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void start() {
        try {
            listPaquete();
            clean();
        } catch (Exception ex) {
            Logger.getLogger(PaqueteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listPaquete() throws Exception {
        PaqueteDao dao;
        try {
            dao = new PaqueteDao();
            LstPaquete = dao.listPaquete();
        } catch (Exception e) {
            throw e;
        }
    }

    public void insert() throws Exception {
        PaqueteDao dao;
        ChipsDao dao2;
        try {
            dao = new PaqueteDao();
            dao2 = new ChipsDao();
            if (paquete.getNumInicio().length() < 18 || paquete.getNumFin().length() < 18) {

                String Valor = paquete.getNumInicio().substring(0, 9); //Extrae la primera parte que es igual para el inicio y fin
                int start = Integer.valueOf(paquete.getNumInicio().substring(paquete.getNumInicio().length() - 9)); //Extrae la segunda partte del inicio
                int end = Integer.valueOf(paquete.getNumFin().substring(paquete.getNumFin().length() - 9)); //Extrae la segunda partte del Final
                if (end - start == 199) {

                    dao.insert(paquete);
                    String CodigoPaquete = dao.buscarCodigo(paquete.getNumInicio(), paquete.getNumFin());

                    int i = 0;
                    while (start <= end) {
                        dao2.insert(Valor + String.valueOf(start), CodigoPaquete);
                        start++;
                        i++;
                        if (i >= 10) {
                            Thread.sleep(150);
                            i = 0;
                        }
                    }
                    listPaquete();
                    clean();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La cantidad de chip es incorrecta"));
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valores Incorrectos"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void clean() {
        paquete = new Paquete();
        paquete.setFecha(String.valueOf(dateFormat.format(fecha)));
    }

    public void facilidad() {
        paquete.setNumFin(paquete.getNumInicio().substring(0, paquete.getNumInicio().length() - 3));
    }

    /**
     * *
     * Getter and Setter
     *
     * @return *
     */
    public List<Paquete> getLstPaquete() {
        return LstPaquete;
    }

    public void setLstPaquete(List<Paquete> LstPaquete) {
        this.LstPaquete = LstPaquete;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

}
