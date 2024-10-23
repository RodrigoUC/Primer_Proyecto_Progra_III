package pos.data;

import pos.logic.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    Database db;

    public ClienteDao() {
        db = Database.instance();
    }

    public void create(Cliente e) throws Exception {
        String sql = "insert into " +
                "Cliente " +
                "(id ,nombre, telefono, email, descuento) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getId());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getTelefono());
        stm.setString(4, e.getEmail());
        stm.setFloat(5, e.getDescuento());
        db.executeUpdate(stm);
    }

    public Cliente read(String id, String nombre) throws Exception {
        String sql = "SELECT * FROM Cliente t WHERE t.id = ? OR t.nombre LIKE ?";
        PreparedStatement stm = db.prepareStatement(sql);

        // Verificamos si se proporcionó un ID. Si no, establecemos un valor nulo.
        if (id != null && !id.isEmpty()) {
            stm.setString(1, id);
        } else {
            stm.setNull(1, java.sql.Types.VARCHAR); // Ajustar según el tipo de dato de ID
        }

        // Configuramos el nombre para la búsqueda con LIKE, incluso si el ID es nulo.
        stm.setString(2, "%" + nombre + "%");

        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "t");
        } else {
            throw new Exception("Cliente NO EXISTE");
        }
    }


    public void update(Cliente e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set nombre=?, telefono=?, email=?, descuento=?" +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setString(3, e.getEmail());
        stm.setFloat(4, e.getDescuento());
        stm.setString(5, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente NO EXISTE");
        }

    }

    public void delete(Cliente e) throws Exception {
        String sql = "delete " +
                "from Cliente " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Cliente NO EXISTE");
        }
    }

    public List<Cliente> search(Cliente e) throws Exception {
        List<Cliente> resultado = new ArrayList<Cliente>();
        String sql = "select * " +
                "from " +
                "Cliente t " +
                "where t.nombre like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getNombre() + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            Cliente r = from(rs, "t");
            resultado.add(r);
        }
        return resultado;
    }

    public Cliente from(ResultSet rs, String alias) throws Exception {
        Cliente e = new Cliente();
        e.setId(rs.getString(alias + ".id"));
        e.setNombre(rs.getString(alias + ".nombre"));
        e.setTelefono(rs.getString(alias + ".telefono"));
        e.setEmail(rs.getString(alias + ".email"));
        e.setDescuento(rs.getFloat(alias + ".descuento"));
        return e;
    }

}

