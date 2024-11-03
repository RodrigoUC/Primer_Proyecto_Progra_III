package pos.logic;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Service implements IService {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    Socket s;
    ObjectInputStream is;
    ObjectOutputStream os;

    String sid;


    private Service(){
        try{
            s=new Socket(Protocol.SERVER,Protocol.PORT);
            os=new ObjectOutputStream(s.getOutputStream());
            is=new ObjectInputStream(s.getInputStream());

            os.writeInt(Protocol.SYNC);
            os.flush();
            sid = (String) is.readObject();
        }
        catch(Exception e){
            System.exit(1);
        }
    }

    public String getSid(){
        return sid;
    }
    public void stop(){
    }



    //================= CAJERO ==============
    public void create(Cajero e) throws Exception {
        os.writeInt(Protocol.CAJERO_CREATE);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){}
        else throw new Exception("CAJERO DUPLICADO");
    }

    public Cajero read(Cajero e) throws Exception {
        os.writeInt(Protocol.CAJERO_READ);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){
            return (Cajero) is.readObject();
        }
        else throw new Exception("CAJERO NO EXISTE");
    }

    public void update(Cajero e) throws Exception {
        os.writeInt(Protocol.CAJERO_UPDATE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("CAJERO NO EXISTE");
    }

    public void delete(Cajero e) throws Exception {
        os.writeInt(Protocol.CAJERO_DELETE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("CAJERO NO EXISTE");
    }

    public List<Cajero> search(Cajero e) {
        try {
            os.writeInt(Protocol.CAJERO_SEARCH);
            os.writeObject(e);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {
                return (List<Cajero>) is.readObject();
            }
        }
        catch(Exception ex){
        }
        return new ArrayList<Cajero>();
    }

    //================= CLIENTE ==============
    public void create(Cliente e) throws Exception {
        os.writeInt(Protocol.CLIENTE_CREATE);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){}
        else throw new Exception("CLIENTE DUPLICADO");
    }

    public Cliente read(Cliente e) throws Exception {
        os.writeInt(Protocol.CLIENTE_READ);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){
            return (Cliente) is.readObject();
        }
        else throw new Exception("CLIENTE NO EXISTE"); }

    public void update(Cliente e) throws Exception {
        os.writeInt(Protocol.CLIENTE_UPDATE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("CLIENTE NO EXISTE");    }

    public void delete(Cliente e) throws Exception {
        os.writeInt(Protocol.CLIENTE_DELETE);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
        else throw new Exception("CLIENTE NO EXISTE");    }

    public List<Cliente> search(Cliente e) {
        try {
            os.writeInt(Protocol.CLIENTE_SEARCH);
            os.writeObject(e);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {
                return (List<Cliente>) is.readObject();
            }
            return new ArrayList<Cliente>();

        }
        catch(Exception ex){
        }
        return new ArrayList<Cliente>();
    }

    //================= PRODUCTOS ============
    public void create(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_CREATE);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){}
        else throw new Exception("PRODUCTO DUPLICADO");    }

    public Producto read(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_READ);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){
            return (Producto) is.readObject();
        }
        else throw new Exception("PRODUCTO NO EXISTE");    }

    public void update(Producto e) throws Exception {
        os.writeInt(Protocol.PRODUCTO_UPDATE);
        os.writeObject(e);
        os.flush();
        if(is.readInt() == Protocol.ERROR_NO_ERROR){}
        else throw new Exception("PRODUCTO NO EXISTE");    }

public void delete(Producto e) throws Exception {
    os.writeInt(Protocol.PRODUCTO_DELETE);
    os.writeObject(e);
    os.flush();
    if(is.readInt() == Protocol.ERROR_NO_ERROR){}
    else throw new Exception("PRODUCTO NO EXISTE");
}

public List<Producto> search(Producto e) {
    try {
        os.writeInt(Protocol.PRODUCTO_SEARCH);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Producto>) is.readObject();
        }
        return new ArrayList<Producto>();
    }
    catch(Exception ex){
    }
    return new ArrayList<Producto>();
}

//================= CATEGORIAS ============
public List<Categoria> search(Categoria e) {
    try {
        os.writeInt(Protocol.CATEGORIA_SEARCH);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Categoria>) is.readObject();
        }
        return new ArrayList<Categoria>();
    }
    catch(Exception ex){
    }
    return new ArrayList<Categoria>();
}


//    -------------------Lineas----------------------------------------------

public void create(Linea e) throws Exception {
    os.writeInt(Protocol.LINEA_CREATE);
    os.writeObject(e);
    os.flush();
    if(is.readInt() == Protocol.ERROR_NO_ERROR){}
    else throw new Exception("LINEA DUPLICADA");    }

public Linea read(Linea e) throws Exception {
    return null;
}

public void update(Linea e) throws Exception {
}

public void delete(Linea e) throws Exception {
}

public List<Linea> search(Linea e) {
    return List.of();
}

    @Override
    public List<Linea> searchbyFactura(int i) {
        try {
            os.writeInt(Protocol.LINEA_SEARCH);
            os.writeInt(i);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {
                return (List<Linea>) is.readObject();
            }
        }
        catch(Exception ex){
        }
        return new ArrayList<Linea>();
    }


//    ------------------------Factura---------------------------------

public void create(Factura e) throws Exception {
    os.writeInt(Protocol.FACTURA_CREATE);
    os.writeObject(e);
    os.flush();
    if(is.readInt() == Protocol.ERROR_NO_ERROR){}
    else throw new Exception("FACTURA DUPLICADA"); }

public Factura read(Factura e) throws Exception {
        return null;
}

public void update(Factura e) throws Exception {
}

public void delete(Factura e) throws Exception {
}

public List<Factura> search(Factura e) {
    try {
        os.writeInt(Protocol.FACTURA_SEARCH);
        os.writeObject(e);
        os.flush();
        if (is.readInt() == Protocol.ERROR_NO_ERROR) {
            return (List<Factura>) is.readObject();
        }
        return new ArrayList<Factura>();
    }
    catch(Exception ex){
    }
    return new ArrayList<Factura>();
}

//-------------------------------Usuario--------------------------------------
public Usuario read(Usuario e) throws Exception {
    os.writeInt(Protocol.USUARIO_READ);
    os.writeObject(e);
    os.flush();
    if(is.readInt() == Protocol.ERROR_NO_ERROR){
        return (Usuario) is.readObject();
    }
    else throw new Exception("USUARIO NO EXISTE...");
}


//-------------------------------Calculos--------------------------------------

public Float[][] estadisticas(List<Categoria> rows, List<String> cols, Rango rango){
    try{
       os.writeInt(Protocol.LINEA_ESTADISTICAS);
       os.writeObject(rows);
       os.writeObject(cols);
       os.writeObject(rango);
       os.flush();
       if (is.readInt() == Protocol.ERROR_NO_ERROR) {
           return (Float[][]) is.readObject();
       }
       return new Float[][]{};
    }catch(Exception e){
        throw new RuntimeException();
    }
}

//Manejo de factura(enviar,recibir, depende de donde se vea)
    public void enviarFactura(Factura factura,Usuario destino) throws Exception {
        try{
            os.writeInt(Protocol.RECIBIR_FACTURA);
            os.writeObject(factura);
            os.writeObject(destino);
            os.flush();
            if (is.readInt() == Protocol.ERROR_NO_ERROR) {}
            else JOptionPane.showMessageDialog(null, "Ocurrio un error desconocido","Informacion" , JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
