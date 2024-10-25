package pos.Logic;

import pos.Data.UsuarioDao;
import pos.logic.*;
import pos.logic.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    ServerSocket srv;
    public Server() {
        try{
            srv = new ServerSocket(1234);
            System.out.println("Servidor iniciado.....");

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void run(){
        Service service = new Service();
        boolean continuar = true;
        try {
            Socket s = srv.accept();
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            int method;
            while (continuar) {
                method=is.readInt();
                System.out.println("Conexion Establecida");
                switch (method) {
                    case Protocol.PRODUCTO_CREATE:
                        try{
                            service.create((Producto) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.PRODUCTO_READ:
                        try{
                            Producto p = service.read((Producto) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(p);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.PRODUCTO_UPDATE:
                        try{
                            service.update((Producto) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.PRODUCTO_DELETE:
                        try{
                            service.delete((Producto) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.PRODUCTO_SEARCH:
                        try{
                            List<Producto> list = service.search((Producto) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(list);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CLIENTE_CREATE:
                        try{
                            service.create((Cliente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CLIENTE_READ:
                        try{
                            Cliente p = service.read((Cliente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(p);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CLIENTE_UPDATE:
                        try{
                            service.update((Cliente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CLIENTE_DELETE:
                        try{
                            service.delete((Cliente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CLIENTE_SEARCH:
                        try{
                            List<Cliente> list = service.search((Cliente) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(list);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CAJERO_CREATE:
                        try{
                            service.create((Cajero) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CAJERO_READ:
                        try{
                            Cajero p = service.read((Cajero) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(p);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CAJERO_UPDATE:
                        try{
                            service.update((Cajero) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CAJERO_DELETE:
                        try{
                            service.delete((Cajero) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.CAJERO_SEARCH:
                        try{
                            Cajero c = (Cajero) is.readObject();
                            List<Cajero> list = service.search(c);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(list);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                        case Protocol.CATEGORIA_SEARCH:
                            try {
                                List<Categoria> list = service.search((Categoria) is.readObject());
                                os.writeInt(Protocol.ERROR_NO_ERROR);
                                os.writeObject(list);
                            }catch (Exception e) {os.writeInt(Protocol.ERROR_ERROR);}
                            break;
                    case Protocol.FACTURA_CREATE:
                        try{
                            service.create((Factura) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.FACTURA_SEARCH:
                        try{
                            List<Factura> list = service.search((Factura) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(list);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.LINEA_CREATE:
                        try{
                            service.create((Linea) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.LINEA_SEARCH:
                        try{
                            List<Linea> list = service.searchbyFactura((is.readInt()));
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(list);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.USUARIO_READ:
                        try{
                            Usuario p = service.read((Usuario) is.readObject());
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(p);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                    case Protocol.LINEA_ESTADISTICAS:
                        try{
                            List<Categoria> rows = (List<Categoria>) is.readObject();
                            List<String> cols = (List<String>) is.readObject();
                            Rango rango = (Rango) is.readObject();
                            Float[][] f = service.estadisticas(rows, cols, rango);
                            os.writeInt(Protocol.ERROR_NO_ERROR);
                            os.writeObject(f);
                        }catch(Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                        break;
                }
                os.flush();
            }
        }
        catch(Exception e){

            }
    }
}
