package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChipsDao extends DAO {

    public void insert(String ICCID, String CodPaquete) throws Exception {
        this.Conectar();
        try {
            String sql = "INSERT INTO CHIP (ICCIDCHIP,CODPAQ,ESTCHIP) VALUES (?,?,'P')";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, ICCID);
            ps.setString(2, CodPaquete);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void updateConfirmacion(String CodChips, String Modalidad, String Empleado) throws Exception {
        this.Conectar();
        try {
            String sql = "Update CHIP set ESTCHIP = 'A' , CODMOD = ? , FECACT = CURRENT_TIMESTAMP , CODEMP = ? WHERE CODCHIP LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, Modalidad);
            ps.setString(2, Empleado);
            ps.setString(3, CodChips);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    //Lista el autocomplete de ubigeo mientras escriben
    public List<String> queryAutoCompleteChips(String a) throws Exception {
        this.Conectar();
        ResultSet rs;
        List<String> lista;
        try {
            String sql = "Select CHIP.ICCIDCHIP from CHIP inner join PAQUETE on PAQUETE.CODPAQ = CHIP.CODPAQ WHERE CHIP.ESTCHIP LIKE 'P' AND CHIP.ICCIDCHIP LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("ICCIDCHIP"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String obteberCodigo(String ICCID) throws Exception {
        this.Conectar();
        ResultSet rs;
        try {
            String sql = "Select CODCHIP from CHIP where ICCIDCHIP like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, ICCID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODCHIP");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
