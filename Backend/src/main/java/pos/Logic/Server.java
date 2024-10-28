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
    public void removeWorker(Worker w){
        workers.remove(w);
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
                case Protocol.ASYNC:
                    sid=(String) is.readObject();
                    System.out.println("ASYNC:"+sid);
                    join(s,os,is,sid);
            }
            os.flush();
            }
        catch(Exception e){System.out.println(e.getMessage());}
        }

    }

    private void join(Socket as, ObjectOutputStream aos, ObjectInputStream ais, String sid) {
        for(Worker w: workers){
            if(w.sid.equals(sid)){
                w.setAs(as,aos,ais);
                break;
            }
        }
    }

    public void deliver_message(Worker from, String message) {
        for (Worker w : workers) {
            if (w != from) w.deliver_message(message);
        }
    }
}
