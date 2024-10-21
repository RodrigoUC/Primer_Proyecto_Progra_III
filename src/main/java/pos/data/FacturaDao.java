package pos.data;

import pos.logic.Factura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacturaDao {
    Database db;

    public FacturaDao() {
        db = Database.instance();
    }

    public void create(Factura e) throws Exception {
        String sql = "insert into " +
                "Factura " +
                "(cajero, cliente) " +
                "values(?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1,e.getCajero().getId());
        stm.setString(2,e.getCliente().getId());
        db.executeUpdate(stm);
    }

    public Factura read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Factura t " +
                "inner join Cajero c on t.cajero=c.id " +
                "and inner join Cliente cl on " +
                "t.cliente=cl.id where t.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        CajeroDao cajeroDao = new CajeroDao();
        ClienteDao clienteDao =new ClienteDao();
        if (rs.next()) {
            Factura r = from(rs, "t");
            r.setCajero(cajeroDao.from(rs, "c"));
            r.setCliente(clienteDao.from(rs, "cl"));
            return r;
        } else {
            throw new Exception("Factura NO EXISTE");
        }
    }

    public void update(Factura e) throws Exception {
        String sql = "update " +
                "Factura " +
                "set cajero=?, cliente=?" +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCajero().getId());
        stm.setString(2, e.getCliente().getId());
        stm.setInt(3, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Factura NO EXISTE");
        }

    }

    public void delete(Factura e) throws Exception {
        String sql = "delete " +
                "from Factura " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Factura NO EXISTE");
        }
    }

    public List<Factura> search(Factura e) throws Exception {
        List<Factura> resultado = new ArrayList<Factura>();
        String sql = "select * " +
                "from " +
                "Factura p " +
                "inner join Cajero c on t.cajero=c.id " +
                "and inner join Cliente cl on " +
                "where p.codigo like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getCodigo() + "%");
        ResultSet rs = db.executeQuery(stm);
        CajeroDao cajeroDao = new CajeroDao();
        ClienteDao clienteDao =new ClienteDao();
        while (rs.next()) {
            Factura r = from(rs, "p");
            r.setCajero(cajeroDao.from(rs, "c"));
            r.setCliente(clienteDao.from(rs, "cl"));
            resultado.add(r);
        }
        return resultado;
    }

    public List<Factura> searchByNombreCliente(String nombreCliente) throws Exception {
        List<Factura> resultado = new ArrayList<>();
        String sql = "select * " +
                "from Factura p " +
                "inner join Cliente cl on p.cliente_id = cl.id " +
                "where cl.nombre like ?";

        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + nombreCliente + "%");  // Buscar coincidencias parciales en el nombre del cliente

        ResultSet rs = db.executeQuery(stm);
        ClienteDao clienteDao = new ClienteDao();

        while (rs.next()) {
            Factura factura = from(rs, "p");  // Obtener la factura desde el ResultSet
            factura.setCliente(clienteDao.from(rs, "cl"));  // Asignar cliente
            resultado.add(factura);  // Añadir factura al resultado
        }

        return resultado;
    }


    public Factura from(ResultSet rs, String alias) throws Exception {
        Factura e = new Factura();
        e.setCodigo(rs.getInt(alias + ".codigo"));
        return e;
    }

}
