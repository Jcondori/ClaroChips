package Dao;

import Models.Modalidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModalidadDao extends DAO {

    public List<Modalidad> listarComboBox() throws Exception {
        this.Conectar();
        List<Modalidad> list;
        ResultSet rs;
        try {
            String sql = "SELECT CODMOD,NOMMOD FROM MODALIDAD WHERE ESTMOD LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            list = new ArrayList();
            Modalidad model;
            while (rs.next()) {
                model = new Modalidad();
                model.setCodigo(rs.getString("CODMOD"));
                model.setModalidad(rs.getString("NOMMOD"));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
