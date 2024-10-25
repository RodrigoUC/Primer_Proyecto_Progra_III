package pos.logic;

public class Protocol {
    public static final String SERVER="localhost";
    public static final int PORT=1234;

    public static final int PRODUCTO_CREATE=101;
    public static final int PRODUCTO_READ=102;
    public static final int PRODUCTO_UPDATE=103;
    public static final int PRODUCTO_DELETE=104;
    public static final int PRODUCTO_SEARCH=105;

    public static final int CATEGORIA_SEARCH=205;

    public static final int CAJERO_CREATE=301;
    public static final int CAJERO_READ=302;
    public static final int CAJERO_UPDATE=303;
    public static final int CAJERO_DELETE=304;
    public static final int CAJERO_SEARCH=305;

    public static final int CLIENTE_CREATE=401;
    public static final int CLIENTE_READ=402;
    public static final int CLIENTE_UPDATE=403;
    public static final int CLIENTE_DELETE=404;
    public static final int CLIENTE_SEARCH=405;

    public static final int FACTURA_CREATE=501;
    public static final int FACTURA_READ=502;
    public static final int FACTURA_UPDATE=503;
    public static final int FACTURA_DELETE=504;
    public static final int FACTURA_SEARCH=505;

    public static final int LINEA_CREATE=601;
    public static final int LINEA_READ=602;
    public static final int LINEA_UPDATE=603;
    public static final int LINEA_DELETE=604;
    public static final int LINEA_SEARCH=605;
    public static final int LINEA_ESTADISTICAS=606;

    public static final int USUARIO_READ=702;



    public static final int ERROR_NO_ERROR=0;
    public static final int ERROR_ERROR=1;
}
