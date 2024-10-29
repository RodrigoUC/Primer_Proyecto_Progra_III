package pos.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketListener {
    ThreadListener listener;
    String sid;
    Socket as;
    ObjectOutputStream aos;
    ObjectInputStream ais;

    public SocketListener(ThreadListener listener, String sid)throws Exception {
        this.listener = listener;
        as = new Socket(Protocol.SERVER, Protocol.PORT);
        this.sid=sid;
        aos = new ObjectOutputStream(as.getOutputStream());
        ais = new ObjectInputStream(as.getInputStream());
        aos.writeObject(Protocol.ASYNC);
        aos.writeObject(sid);
        aos.flush();
    }

    boolean condition = true;
    private Thread t;
    public void start() {
        t=new Thread(new Runnable() {
            public void run() {listen();}
        });
        condition = true;
        t.start();
    }

    public void stop() {
        condition = false;
    }

    private void listen() {
        int method;
        while(condition) {
            try{
               method = ais.readInt();
            switch (method) {
                case Protocol.USUARIO_INICIO:
                    try{


                    }catch(Exception e){}
                case Protocol.USUARIO_SALIO:


                case Protocol.RECIBIR_FACTURA:


            }
            }
            catch (IOException ex){condition = false;}
        }
        try{
            as.shutdownOutput();
            as.close();
        }catch(IOException ex){}
    }

    private void actualizarListaUsuario(List<Usuario> usuarios) {

    }

}
