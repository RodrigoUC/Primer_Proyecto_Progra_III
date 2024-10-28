package pos.Data;

import pos.logic.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    pos.Data.Database db;

    public ProductoDao() {
        db = pos.Data.Database.instance();
    }

    public void create(Producto e) throws Exception {
        String sql = "insert into " +
                "Producto " +
                "(codigo ,descripcion, unidad, existencia, precio,categoria) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getDescripcion());
        stm.setString(3, e.getUnidad());
        stm.setInt(4, e.getExistencia());
        stm.setFloat(5, e.getPrecio());
        stm.setString(6, e.getCategoria().getId());
        db.executeUpdate(stm);
    }

    public Producto read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Producto t " +
                "inner join Categoria c on t.categoria=c.id " +
                "where t.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        pos.Data.CategoriaDao categoriaDao=new pos.Data.CategoriaDao();
        if (rs.next()) {
            Producto r = from(rs, "t");
            r.setCategoria(categoriaDao.from(rs, "c"));
            return r;
        } else {
            throw new Exception("Producto NO EXISTE");
        }
    }

    public void update(Producto e) throws Exception {
        String sql = "update " +
                "Producto " +
                "set descripcion=?, unidad=?, existencia=?, precio=?, categoria=? " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getDescripcion());
        stm.setString(2, e.getUnidad());
        stm.setInt(3, e.getExistencia());
        stm.setFloat(4, e.getPrecio());
        stm.setString(5, e.getCategoria().getId());
        stm.setString(6, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Producto NO EXISTE");
        }

    }

    public void delete(Producto e) throws Exception {
        String sql = "delete " +
                "from Producto " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Producto NO EXISTE");
        }
    }

    public List<Producto> search(Producto e) throws Exception {
        List<Producto> resultado = new ArrayList<Producto>();
        String sql = "select * " +
                "from " +
                "Producto p " +
                "inner join Categoria c on p.categoria=c.id " +
                "where p.descripcion like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + e.getDescripcion() + "%");
        ResultSet rs = db.executeQuery(stm);
        pos.Data.CategoriaDao categoriaDao=new pos.Data.CategoriaDao();
        while (rs.next()) {
            Producto r = from(rs, "p");
            r.setCategoria(categoriaDao.from(rs, "c"));
            resultado.add(r);
        }
        return resultado;
    }

    public Producto from(ResultSet rs, String alias) throws Exception {
        Producto e = new Producto();
        e.setCodigo(rs.getString(alias + ".codigo"));
        e.setDescripcion(rs.getString(alias + ".descripcion"));
        e.setUnidad(rs.getString(alias + ".unidad"));
        e.setPrecio(rs.getFloat(alias + ".precio"));
        e.setExistencia(rs.getInt(alias + ".existencia"));
        return e;
    }

    public Producto historico(ResultSet rs, String alias) throws Exception {
        Producto e = new Producto();
        e.setCodigo(rs.getString(alias + ".codigo_producto"));
        e.setDescripcion(rs.getString(alias + ".descripcion"));
        e.setUnidad(rs.getString(alias + ".unidad"));
        e.setPrecio(rs.getFloat(alias + ".precio"));
        e.setExistencia(rs.getInt(alias + ".existencia"));
        return e;
    }
}
