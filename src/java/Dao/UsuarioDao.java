package Dao;

import Models.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends DAO{
    
    public Usuario startSession(String user, String pass) throws Exception {
        this.Conectar();
        Usuario model = null;
        ResultSet rs;
        try {
            String sql = "SELECT CODUS,USERUS,NOMUS,ESTUS FROM USUARIO WHERE USERUS LIKE ? AND PASSUS LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                model = new Usuario();
                model.setCodigo(rs.getString("CODUS"));
                model.setUsername(rs.getString("USERUS"));
                model.setNombre(rs.getString("NOMUS"));
                model.setEstado(rs.getString("ESTUS"));
            }
            return model;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
}
