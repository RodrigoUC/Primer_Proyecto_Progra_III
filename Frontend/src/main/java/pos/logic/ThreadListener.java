package pos.logic;

import java.util.List;

public interface ThreadListener {
    public void salioUsuario(Usuario usuario);
    public void ingresoUsuario(Usuario usuario);
    public void recibirFactura(Factura factura,Usuario usuario);
}
