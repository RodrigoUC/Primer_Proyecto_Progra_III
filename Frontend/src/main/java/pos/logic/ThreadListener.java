package pos.logic;

import java.util.List;

public interface ThreadListener {
    public void acttualizarListaUsuarios(List<Usuario> usuarios);
    public void recibirFactura(Factura factura,String id);
}
