package pos.data;

import pos.logic.Linea;
import pos.logic.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//codigo, Producto producto, int cantidad, double descuento
public class LineaDao {
    Database db;

    public LineaDao() {
        db = Database.instance();
    }

    public void create(Linea e) throws Exception {
        String sql = "insert into " +
                "Linea " +
                "(codigo ,producto, cantidad, descuento) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getProducto().getCodigo());
        stm.setInt(3, e.getCantidad());
        stm.setDouble(4, e.getDescuento());
        db.executeUpdate(stm);
    }

    public Linea read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Linea l " +
                "where l.codigo=?  or l.codigo like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        //stm.setString(2, "%" + nombre + "%");
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "l");
        } else {
            throw new Exception("Linea NO EXISTE");
        }
    }

    public void update(Linea e) throws Exception {
        String sql = "update " +
                "Linea " +
                "set codigo=?, producto=?, cantidad=?, descuento=?" +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getProducto().getCodigo());
        stm.setInt(3, e.getCantidad());
        stm.setDouble(4, e.getDescuento());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Linea NO EXISTE");
        }

    }

    public void delete(Linea e) throws Exception {
        String sql = "delete " +
                "from Linea " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Linea NO EXISTE");
        }
    }

    public List<Linea> search(Linea e) throws Exception {
        List<Linea> resultado = new ArrayList<Linea>();
        String sql = "select * " +
                "from " +
                "Linea l " +
                "inner join Producto p on l.producto=p.codigo " +
                "where p.codigo like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getCodigo() + "%");
        ResultSet rs = db.executeQuery(stm);
        ProductoDao productoDao=new ProductoDao();
        while (rs.next()) {
            Linea r = from(rs, "l");
            r.setProducto(productoDao.from(rs, "p"));
            resultado.add(r);
        }
        return resultado;
    }

    public Linea from(ResultSet rs, String alias) throws Exception {
        Linea e = new Linea();
        e.setId(rs.getString(alias + ".id"));
        //e.setProducto(rs.getString(alias + ".producto"));
        e.setCantidad(Integer.parseInt(rs.getString(alias + ".cantidad")));
        e.setDescuento(Double.parseDouble(rs.getString(alias + ".descuento")));

        return e;
    }

}

