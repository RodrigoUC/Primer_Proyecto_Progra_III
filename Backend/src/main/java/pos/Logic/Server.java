package pos.Logic;

import pos.Data.UsuarioDao;
import pos.logic.*;
import pos.logic.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    ServerSocket srv;
    List<Worker> workers;
    public Server() {
        try{
            srv = new ServerSocket(Protocol.PORT);
            workers= Collections.synchronizedList(new ArrayList<Worker>());
            System.out.println("Servidor iniciado.....");

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public synchronized void removeWorker(Worker w){
        workers.remove(w);
        for(Worker w2: workers){
            if(w2 != w && w2.getUsuario() != null && w.getUsuario() != null){
                w2.usuarioSalio(w.getUsuario());
            }
        }
        System.out.println("Cliente desconectado");
        System.out.println("Quedan:"+workers.size());
    }

    public void run(){
        Service service = new Service();
        boolean continuar = true;
        Socket s;
        Worker worker;
        String sid;
        while (continuar) {
        try {
            s = srv.accept();
            System.out.println("Conexion Establecida");
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            int type = is.readInt();
            switch (type){
                case Protocol.SYNC:
                    sid = s.getRemoteSocketAddress().toString();
                    System.out.println("SYNCH:"+sid);
                    worker = new Worker(this,s,os,is,sid,Service.instance());
                    workers.add(worker);
                    System.out.println("Quedan:"+workers.size());
                    worker.start();
                    os.writeObject(sid);
                    os.flush();
                    break;
                case Protocol.ASYNC:
                    sid=(String) is.readObject();
                    System.out.println("ASYNC:"+sid);
                    join(s,os,is,sid);
                    break;
            }
            }
        catch(Exception e){System.out.println(e.getMessage());}
        }

    }

    private synchronized void join (Socket as, ObjectOutputStream aos, ObjectInputStream ais, String sid) {
        for(Worker w: workers){
            if(w.sid.equals(sid)){
                w.setAs(as,aos,ais);
                break;
            }
        }
    }

    public synchronized void updateWorkers(Worker w){   //Notifica a los otros workers que se conecto otro worker
        for(Worker w2: workers){
            if(w2.getUsuario()!= null && w.getUsuario().getID() != w2.getUsuario().getID()) {
                w2.usuarioInicio(w.getUsuario());
            }
        }
    }
    public synchronized void updateNewWorker(Worker w){     //Manda a agregar todos los usuarios de los workers que ya existen
        for(Worker w2: workers){
            if(w2.getUsuario() != null && w.getUsuario().getID() != w2.getUsuario().getID()) {
                w.usuarioInicio(w2.getUsuario());
            }
        }
    }
   public void asignarFactura(Factura f, Usuario destino,Usuario origen)throws Exception{
        for (Worker w: workers){
            if (w.getUsuario() != null && w.getUsuario().getID().equals(destino.getID())) {
                w.enviarFactura(f,origen);
            }
        }
   }
}
