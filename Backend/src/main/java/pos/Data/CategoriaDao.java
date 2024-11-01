package pos.Data;

import pos.logic.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    private pos.Data.Database db;

    public CategoriaDao() {
        db = pos.Data.Database.instance();
    }

    public List<Categoria> search(Categoria e) throws Exception {
        List<Categoria> resultado = new ArrayList<Categoria>();
        String sql = "select * " +
                "from " +
                "Categoria t " +
                "where t.nombre like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getNombre() + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            Categoria r = from(rs, "t");
            resultado.add(r);
        }
        return resultado;
    }

    public Categoria read(String id) throws Exception {
        String sql = "SELECT * FROM Categoria t WHERE t.id = ?";
        PreparedStatement stm = db.prepareStatement(sql);

        // Verificamos si se proporcionó un ID. Si no, establecemos un valor nulo.
        if (id != null && !id.isEmpty()) {
            stm.setString(1, id);
        } else {
            stm.setNull(1, java.sql.Types.VARCHAR); // Ajustar según el tipo de dato de ID
        }

        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "t");
        } else {
            throw new Exception("Categotria NO EXISTE");
        }
    }

    public Categoria from(ResultSet rs, String alias) throws Exception {
        Categoria e = new Categoria();
        e.setId(rs.getString(alias + ".id"));
        e.setNombre(rs.getString(alias + ".nombre"));
        return e;
    }

}