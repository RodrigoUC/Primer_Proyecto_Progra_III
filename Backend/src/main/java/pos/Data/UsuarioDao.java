package pos.Data;

import pos.logic.Cliente;
import pos.logic.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    Database db;

    public UsuarioDao() {
        db = Database.instance();
    }


    public Usuario read(String id, String contrasena) throws Exception {
        String sql = "SELECT * FROM Usuario t WHERE t.id = ? AND t.contrasena = ?";
        PreparedStatement stm = db.prepareStatement(sql);

        stm.setString(1, id);
        stm.setString(2, contrasena);

        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "t");
        } else {
            throw new Exception("Usuario NO EXISTE.");
        }
    }

    public Usuario from(ResultSet rs, String alias) throws Exception {
        Usuario e = new Usuario();
        e.setID(rs.getString(alias + ".id"));
        e.setContrasena(rs.getString(alias + ".contrasena"));
        return e;
    }
}
