package Dao;

import Models.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao extends DAO{
           
    public List<Empleado> listEmpleado() throws Exception {
        this.Conectar();
        List<Empleado> list;
        ResultSet rs;
        try {
            String sql = "Select CODEMP,DNIEMP,NOMEMP,APEEMP,ESTEMP,PRIEMP,USUEMP from EMPLEADO WHERE ESTEMP LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            list = new ArrayList();
            Empleado model;
            while (rs.next()) {
                model = new Empleado();
                model.setCodigo(rs.getString("CODEMP"));
                model.setDNI(rs.getString("DNIEMP"));
                model.setNombre(rs.getString("NOMEMP"));
                model.setApellido(rs.getString("APEEMP"));
                model.setEstado(rs.getString("ESTEMP"));
                model.setUsuario(rs.getString("USUEMP"));
                model.setPrivilegio(rs.getString("PRIEMP"));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public void insert(Empleado model) throws Exception{
        this.Conectar();
        try {
            String sql = "Insert into EMPLEADO(DNIEMP,NOMEMP,APEEMP,USUEMP,PASEMP,PRIEMP,ESTEMP) values (?,?,?,?,?,?,'A')";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, model.getDNI());
            ps.setString(2, model.getNombre());
            ps.setString(3, model.getApellido());
            ps.setString(4, model.getUsuario());
            ps.setString(5, model.getPassword());
            ps.setString(6, model.getPrivilegio());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void update(Empleado model) throws Exception{
        this.Conectar();
        try {
            String sql = "UPDATE EMPLEADO SET DNIEMP=? , NOMEMP=? , APEEMP=? , USUEMP=? , PRIEMP=? WHERE CODEMP=?  ";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, model.getDNI());
            ps.setString(2, model.getNombre());
            ps.setString(3, model.getApellido());
            ps.setString(4, model.getUsuario());
            ps.setString(6, model.getPrivilegio());
            ps.setString(7, model.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
