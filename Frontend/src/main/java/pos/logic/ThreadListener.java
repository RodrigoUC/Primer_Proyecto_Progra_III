package pos.logic;

import java.util.List;

public interface ThreadListener {
    void salioUsuario(Usuario usuario);
    void ingresoUsuario(Usuario usuario);
    void recibirFactura(Factura factura, Usuario usuario);
}
