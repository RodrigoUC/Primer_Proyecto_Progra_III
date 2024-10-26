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
        while (continuar) {
        try {
            s = srv.accept();
            System.out.println("Conexion Establecida");
            worker = new Worker(this,s,service);
            workers.add(worker);
            System.out.println("Quedan:"+workers.size());
            worker.start();
            }
        catch(Exception e){System.out.println(e.getMessage());}
        }

    }
}
