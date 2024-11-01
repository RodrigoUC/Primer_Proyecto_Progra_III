package pos.data;

import pos.Data.ProductoDao;
import pos.logic.Rango;
import pos.logic.Linea;
import pos.logic.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LineaDao {
    pos.Data.Database db;

    public LineaDao() {
        db = pos.Data.Database.instance();
    }

    public void create(Linea e) throws Exception {
        String sql = "insert into " +
                "Linea " +
                "(producto, factura, cantidad, descuento) " +
                "values(?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getProducto().getCodigo());
        stm.setInt(2, e.getFactura().getCodigo());
        stm.setInt(3, e.getCantidad());
        stm.setDouble(4, e.getDescuento());
        int numero = db.executeUpdateWithKeys(stm);
        e.setId(numero);
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
        ProductoDao productoDao = new ProductoDao();
        while (rs.next()) {
            Linea r = from(rs, "l");
            r.setProducto(productoDao.from(rs, "p"));
            resultado.add(r);
        }
        return resultado;
    }

    public List<Linea> searchByFactura(int codigoFactura) throws Exception {
        List<Linea> resultado = new ArrayList<>();
        String sql = "SELECT l.codigo AS codigo_linea, l.cantidad, l.descuento, " +
                "p.codigo AS codigo_producto, p.descripcion, p.unidad, p.precio, p.existencia, " +
                "p.categoria " +
                "FROM Linea l " +
                "INNER JOIN Producto p ON l.producto = p.codigo " +
                "WHERE l.factura = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, codigoFactura);
        ResultSet rs = db.executeQuery(stm);

        ProductoDao productoDao = new ProductoDao();  // Para obtener el producto de cada linea.
        while (rs.next()) {
            Linea r = historico(rs, "l");  // Map los datos de la tabla Linea.
            r.setProducto(productoDao.historico(rs, "p"));
            resultado.add(r);
        }
        return resultado;
    }


    public Linea from(ResultSet rs, String alias) throws Exception {
        Linea e = new Linea();
        e.setId(rs.getInt(alias + ".codigo"));
        e.setCantidad(Integer.parseInt(rs.getString(alias + ".cantidad")));
        e.setDescuento(Float.parseFloat(rs.getString(alias + ".descuento")));

        return e;
    }

    public Linea historico(ResultSet rs, String alias)throws Exception{
        Linea e = new Linea();
        e.setId(rs.getInt(alias + ".codigo_linea"));
        e.setCantidad(Integer.parseInt(rs.getString(alias + ".cantidad")));
        e.setDescuento(Float.parseFloat(rs.getString(alias + ".descuento")));

        return e;
    }


    public Float[][] estadisticas(List<Categoria> rows, List<String> cols, Rango rango) throws Exception {
        // Inicializa la matriz de resultados
        Float[][] resultado = new Float[rows.size()][cols.size()];

        // Asegurarse de que la matriz esté llena de ceros inicialmente (opcional)
        for (int i = 0; i < rows.size(); i++) {
            Arrays.fill(resultado[i], 0.0f);  // Llena con 0 en caso de que no haya datos para una celda específica
        }

        if (rows.isEmpty() || cols.isEmpty()) return resultado;  // Asegúrate de que hay filas y columnas

        // SQL con filtrado por año y mes
        String sql = "SELECT " +
                "c.id AS categoria, " +
                "CONCAT(YEAR(f.fecha), '-', LPAD(MONTH(f.fecha), 2, '0')) AS periodo, " +
                "SUM(l.cantidad * p.precio * (1 - l.descuento / 100)) AS total " +
                "FROM linea l " +
                "INNER JOIN factura f ON l.factura = f.codigo " +
                "INNER JOIN producto p ON l.producto = p.codigo " +
                "INNER JOIN categoria c ON p.categoria = c.id " +
                "WHERE YEAR(f.fecha) BETWEEN ? AND ? " +
                "AND MONTH(f.fecha) BETWEEN ? AND ? " +
                "AND c.id IN (" + "?,".repeat(rows.size()).substring(0, "?,".repeat(rows.size()).length() - 1) + ") " +
                "GROUP BY categoria, periodo " +
                "ORDER BY categoria, periodo";  // Ordena por categoría y periodo

        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, rango.getAnioDesde());
        stm.setInt(2, rango.getAnioHasta());
        stm.setInt(3, rango.getMesDesde());
        stm.setInt(4, rango.getMesHasta());

        // Setear los valores de las categorías
        for (int i = 0; i < rows.size(); i++) {
            stm.setString(5 + i, rows.get(i).getId());  // Asume que getId() devuelve el ID de la categoría
        }

        try (ResultSet rs = db.executeQuery(stm)) {
            // Recorrer el ResultSet
            while (rs.next()) {
                String categoria = rs.getString("categoria");
                String periodo = rs.getString("periodo");
                Float total = rs.getFloat("total");

                // Buscar el índice de la categoría en 'rows'
                int rowIndex = -1;
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getId().equals(categoria)) {
                        rowIndex = i;
                        break;
                    }
                }

                // Buscar el índice del periodo en 'cols'
                int colIndex = cols.indexOf(periodo);  // cols debe contener los periodos como '2024-10', '2024-11', etc.

                if (rowIndex != -1 && colIndex != -1) {
                    // Asignar el total en la matriz resultado
                    resultado[rowIndex][colIndex] = total;
                }
            }

            // Retornar el arreglo bidimensional lleno con los totales
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }
}

