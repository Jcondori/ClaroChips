package Controller;

import Dao.ChipsDao;
import Models.Chips;
import Models.Empleado;
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

@Named(value = "chipsController")
@SessionScoped
public class ChipsController implements Serializable {

    Chips chips = new Chips();
    List<Chips> EmpleadosCantidad = new ArrayList();
    List<Chips> ListaFechas = new ArrayList();

    java.util.Date FechaActual = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    String Fecha = null;
    String TotalChipsActivados = "0";

    @PostConstruct
    public void start() {
        try {
            listarFechas();
            listarEmpleadosCantidad();
            contarChipsActivados();
        } catch (Exception ex) {
            Logger.getLogger(ChipsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarDashboard() throws Exception{
        contarChipsActivados();
        listarEmpleadosCantidad();
    }

    public List<String> autocompleteICCID(String query) throws Exception {
        ChipsDao dao = new ChipsDao();
        return dao.queryAutoCompleteChips(query);
    }

    public void listarEmpleadosCantidad() throws Exception {
        ChipsDao dao;
        try {
            dao = new ChipsDao();
            EmpleadosCantidad = dao.listarCantidades(validarFecha());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void contarChipsActivados() throws Exception{
        ChipsDao dao;
        try {
            dao = new ChipsDao();
            TotalChipsActivados = dao.totalChipActivados();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarFechas() throws Exception {
        ChipsDao dao;
        try {
            dao = new ChipsDao();
            ListaFechas = dao.listarFechas();
        } catch (Exception e) {
            throw e;
        }
    }

    public String validarFecha() {
        if (Fecha != null) {
            return Fecha;
        } else {
            return dateFormat.format(FechaActual);
        }

    }

    public void confirmarChip() throws Exception {
        ChipsDao dao;
        try {
            dao = new ChipsDao();
            Empleado us = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            dao.updateConfirmacion(dao.obteberCodigo(chips.getICCID()), chips.getCodModalidad(), us.getCodigo());
            clean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Chip Activado"));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Getter and Setter
     */
    public void clean() {
        chips = new Chips();
    }

    public Chips getChips() {
        return chips;
    }

    public void setChips(Chips chips) {
        this.chips = chips;
    }

    public List<Chips> getEmpleadosCantidad() {
        return EmpleadosCantidad;
    }

    public void setEmpleadosCantidad(List<Chips> EmpleadosCantidad) {
        this.EmpleadosCantidad = EmpleadosCantidad;
    }

    public List<Chips> getListaFechas() {
        return ListaFechas;
    }

    public void setListaFechas(List<Chips> ListaFechas) {
        this.ListaFechas = ListaFechas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getTotalChipsActivados() {
        return TotalChipsActivados;
    }

    public void setTotalChipsActivados(String TotalChipsActivados) {
        this.TotalChipsActivados = TotalChipsActivados;
    }

}
