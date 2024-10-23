package pos.data;

import pos.logic.Factura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
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
                "(cajero, cliente, fecha) " +
                "values(?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1,e.getCajero().getId());
        stm.setString(2,e.getCliente().getId());
        stm.setString(3, e.getFecha().format(DateTimeFormatter.ofPattern("yyyy/MM/dd)")));
        int numero = db.executeUpdateWithKeys(stm);
        e.setCodigo(numero);
    }

    public Factura read(Integer codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Factura t " +
                "inner join Cajero c on t.cajero=c.id " +
                "and inner join Cliente cl on " +
                "t.cliente=cl.id where t.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, codigo);
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
        stm.setInt(1, e.getCodigo());
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

    public List<Factura> searchByClienteID(Factura e) throws Exception {
        List<Factura> resultado = new ArrayList<>();
        String sql = "select " +
                "* " +
                "from Factura t " +
                "inner join Cliente cl on t.cliente = cl.id " +
                "inner join Cajero c on t.cajero = c.id " +
                "where cl.id = ?";

        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(e.getCliente().getId()));  // Usamos el ID del cliente para la búsqueda
        ResultSet rs = db.executeQuery(stm);

        CajeroDao cajeroDao = new CajeroDao();
        ClienteDao clienteDao = new ClienteDao();

        while (rs.next()) {
            Factura r = from(rs, "t");
            r.setCajero(cajeroDao.from(rs, "c"));
            r.setCliente(clienteDao.from(rs, "cl"));
            resultado.add(r);
        }

        return resultado;
    }




    public Factura from(ResultSet rs, String alias) throws Exception {
        Factura e = new Factura();
        e.setCodigo(rs.getInt(alias + ".codigo"));
        return e;
    }

}
