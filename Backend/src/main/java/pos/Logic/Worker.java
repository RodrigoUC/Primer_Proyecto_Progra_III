package pos.Logic;

import pos.logic.IService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Worker {
    Server srv;
    Socket s;
    ObjectInputStream is;
    ObjectOutputStream os;
    IService service;

    String sid;
    Socket as;
    ObjectOutputStream aos;
    ObjectInputStream ais;

    public Worker(Server server, Socket s,ObjectOutputStream os,ObjectInputStream is,String sid,IService service) {
        this.srv = server;
        this.s = s;
        this.os = os;
        this.is = is;
        this.sid = sid;
        this.service = service;
    }
    public void setAs(Socket as,ObjectOutputStream os,ObjectInputStream is) {
        this.as = as;
        this.os = aos;
        this.is = ais;
    }


}
