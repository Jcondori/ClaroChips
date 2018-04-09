package Dao;

import Models.Chips;
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

    public List<Chips> listarCantidades(String fecha) throws Exception {
        this.Conectar();
        ResultSet rs;
        List<Chips> list;
        try {
            String sql = "Select (EMPLEADO.NOMEMP || ' ' || EMPLEADO.APEEMP) AS NOMBRE , COUNT(EMPLEADO.NOMEMP || ' ' || EMPLEADO.APEEMP) AS CANTIDAD from CHIP INNER JOIN EMPLEADO ON EMPLEADO.CODEMP = CHIP.CODEMP where TO_DATE(TO_CHAR(FECACT,'DD/MM/YYYY'),'DD/MM/YYYY') LIKE TO_DATE(?,'DD/MM/YYYY') GROUP BY (EMPLEADO.NOMEMP || ' ' || EMPLEADO.APEEMP)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            list = new ArrayList();
            Chips model;
            while (rs.next()) {
                model = new Chips();
                model.setNomEmpleado(rs.getString("NOMBRE"));
                model.setEstado(rs.getString("CANTIDAD"));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Chips> listarFechas() throws Exception {
        this.Conectar();
        ResultSet rs;
        List<Chips> list;
        try {
            String sql = "Select DISTINCT TO_CHAR(FECACT,'DD/MM/YYYY') AS FECHAS from CHIP WHERE TO_DATE(TO_CHAR(FECACT,'DD/MM/YYYY'),'DD/MM/YYYY') NOT LIKE TO_DATE(SYSDATE,'DD/MM/YYYY') ORDER BY TO_DATE(TO_CHAR(FECACT,'DD/MM/YYYY'),'DD/MM/YYYY') DESC";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            list = new ArrayList();
            Chips model;
            while (rs.next()) {
                model = new Chips();
                model.setFecha(rs.getString("FECHAS"));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    //Lista el autocomplete de chhips mientras escriber
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

    public String totalChipActivados() throws Exception {
        this.Conectar();
        ResultSet rs;
        try {
            String sql = "Select COUNT(*) AS TOTAL from CHIP WHERE CODEMP IS NOT NULL";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TOTAL");
            }
            return "0";
        } catch (SQLException e) {
            throw e;
        }

    }

}
