package Dao;

import Models.Paquete;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDao extends DAO {

    public void insert(Paquete model) throws Exception {
        this.Conectar();
        try {
            String sql = "INSERT INTO PAQUETE (NUMINI,NUNFIN,FECPAQ,ESTPAQ) VALUES (?,?,TO_DATE(?,'DD/MM/YYYY'),'A')";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, model.getNumInicio());
            ps.setString(2, model.getNumFin());
            ps.setString(3, model.getFecha());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String buscarCodigo(String Inicio, String Fin) throws Exception {
        this.Conectar();
        ResultSet rs;
        try {
            String sql = "Select CODPAQ from PAQUETE WHERE NUMINI LIKE ? AND NUNFIN LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Inicio);
            ps.setString(2, Fin);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODPAQ");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Paquete> listPaquete() throws Exception {
        this.Conectar();
        List<Paquete> list;
        ResultSet rs;
        try {
            String sql = "SELECT PAQUETE.CODPAQ,PAQUETE.NUMINI,PAQUETE.NUNFIN,PAQUETE.ESTPAQ,TO_CHAR(FECPAQ,'DD/MON/YYYY') as FECPAQ FROM PAQUETE";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            list = new ArrayList();
            Paquete model;
            while (rs.next()) {
                model = new Paquete();
                model.setCodigo(rs.getString("CODPAQ"));
                model.setNumInicio(rs.getString("NUMINI"));
                model.setNumFin(rs.getString("NUNFIN"));
                model.setFecha(rs.getString("FECPAQ"));
                model.setEstado(rs.getString("ESTPAQ"));
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
