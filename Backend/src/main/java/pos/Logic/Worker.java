package pos.Logic;

import pos.logic.*;
import pos.logic.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Worker {
    Server srv;
    Socket s;
    ObjectInputStream is;
    ObjectOutputStream os;
    Service service;

    String sid;
    Socket as;
    ObjectOutputStream aos;
    ObjectInputStream ais;
    Usuario usuario;



    public Worker(Server srv, Socket s,ObjectOutputStream os,ObjectInputStream is,String sid,Service service) {
            this.srv = srv;
            this.s = s;
            this.os =os;
            this.is = is;
            this.service = service;
            this.sid = sid;
    }
    public void setAs(Socket as,ObjectOutputStream aos,ObjectInputStream ais) {
        this.as = as;
        this.aos = aos;
        this.ais = ais;
    }

    boolean continuar;
    public void start(){
        try{
            System.out.println("Worker atentiendo peticiones...");
            Thread t  = new Thread(new Runnable() {
                public void run() {listen();}
            });
            continuar = true;
            t.start();
        }catch(Exception e){}
    }
    public void stop(){
        continuar = false;
            srv.removeWorker(this);
    }

    public Usuario getUsuario(){
        return usuario;
    }

    private void listen() {
        int method;
        while (continuar) {
        try {
            method=is.readInt();
            System.out.println("Operacion:"+method);
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
                            usuario = p;
                            if(srv.workers.size() > 1) {
                                srv.updateWorkers(this);
                                srv.updateNewWorker(this);
                            }
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
                case Protocol.RECIBIR_FACTURA:  //Enviar factura, pero en si el proceso es para que alguien reciba la factura
                    try {
                        Factura f = (Factura) is.readObject();
                        Usuario destino = (Usuario) is.readObject();
                        srv.asignarFactura(f,destino,usuario);
                        os.writeInt(Protocol.ERROR_NO_ERROR);
                    }catch (Exception e){os.writeInt(Protocol.ERROR_ERROR);}
                }
                os.flush();
            }
        catch(Exception e){
            stop();
        }
        }

    }

    public void usuarioSalio(Usuario u) {
        try {
            aos.writeInt(Protocol.USUARIO_SALIO);
            aos.writeObject(u);
            aos.flush();
        }
        catch (Exception e) {}
    }
    public void usuarioInicio(Usuario u) {
        try {
            aos.writeInt(Protocol.USUARIO_INICIO);
            aos.writeObject(u);
            aos.flush();
        }catch (Exception e) {}
    }


    public void enviarFactura(Factura f, Usuario origen) {
        try{
            aos.writeInt(Protocol.RECIBIR_FACTURA);
            aos.writeObject(f);
            aos.writeObject(origen);
            aos.flush();
        }catch (Exception e){}
    }
}
