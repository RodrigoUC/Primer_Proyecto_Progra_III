package pos.data;

import pos.logic.*;
import jakarta.xml.bind.annotation.*;
import pos.logic.LineaEstadistica;
import pos.logic.LineaHistorico;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElementWrapper(name = "clientes")
    @XmlElement(name = "cliente")
    private List<Cliente> clientes;

    @XmlElementWrapper(name = "cajero")
    @XmlElement(name = "cajero")
    private List<Cajero> cajero;

    @XmlElementWrapper(name = "productos")
    @XmlElement(name = "producto")
    private List<Producto> productos;

    @XmlElementWrapper(name = "categorias")
    @XmlElement(name = "categoria")
    private List<Categoria> categorias;

    @XmlElementWrapper(name = "lineas")
    @XmlElement(name = "linea")
    private List<Linea> lineas;

    @XmlElementWrapper(name = "facturas")
    @XmlElement(name = "factura")
    private List<Factura> facturas;

    @XmlElementWrapper(name = "fechas")
    @XmlElement(name = "fecha")
    private List<Fecha> fechas;

    @XmlElementWrapper(name = "lineasHistoricas")
    @XmlElement(name = "lineasHistoricas")
    private List<LineaHistorico> lineasHistoricas;

    public Data() {
        clientes = new ArrayList<>();
        cajero = new ArrayList<>();
        productos = new ArrayList<>();
        categorias = new ArrayList<>();
        lineas = new ArrayList<>();
        facturas = new ArrayList<>();
        fechas = new ArrayList<>();
        lineasHistoricas = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Cajero> getCajeros() { return cajero; }

    public List<Producto> getProductos() { return productos; }

    public List<Categoria> getCategorias() { return categorias; }

    public List<Linea> getLineas() { return lineas; }

    public List<Factura> getFacturas() { return facturas; }

    public List<Fecha> getFechas() { return fechas; }

    public List<LineaHistorico> getLineasHistoricas() { return lineasHistoricas; }
}
