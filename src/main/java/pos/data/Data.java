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

    @XmlElementWrapper(name = "lineas")
    @XmlElement(name = "linea")
    private List<Linea> lineas;

    @XmlElementWrapper(name = "lineasEstadisticas")
    @XmlElement(name = "lineaEstadistica")
    private List<LineaEstadistica> lineasEstadisticas;

    @XmlElementWrapper(name = "lineasHistoricas")
    @XmlElement(name = "lineasHistoricas")
    private List<LineaHistorico> lineasHistoricas;

    public Data() {
        clientes = new ArrayList<>();
        cajero = new ArrayList<>();
        productos = new ArrayList<>();
        lineas = new ArrayList<>();
        lineasEstadisticas = new ArrayList<>();
        lineasHistoricas = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Cajero> getCajeros() { return cajero; }

    public List<Producto> getProductos() { return productos; }

    public List<Linea> getLineas() { return lineas; }

    public List<LineaEstadistica> getLineasEstadisticas() { return lineasEstadisticas; }

    public List<LineaHistorico> getLineasHistoricas() { return lineasHistoricas; }
}
