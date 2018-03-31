package Controller;

import Dao.ChipsDao;
import Models.Chips;
import Models.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;

@Named(value = "chipsController")
@SessionScoped
public class ChipsController implements Serializable {

    Chips chips = new Chips();

    public List<String> autocompleteICCID(String query) throws Exception {
        ChipsDao dao = new ChipsDao();
        return dao.queryAutoCompleteChips(query);
    }

    public void confirmarChip() throws Exception {
        ChipsDao dao;
        try {
            dao = new ChipsDao();
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            dao.updateConfirmacion(dao.obteberCodigo(chips.getICCID()), chips.getCodModalidad(), us.getCodigo());
            clean();
        } catch (Exception e) {
            throw e;
        }
    }

    public void clean() {
        chips = new Chips();
    }

    public Chips getChips() {
        return chips;
    }

    public void setChips(Chips chips) {
        this.chips = chips;
    }

}
