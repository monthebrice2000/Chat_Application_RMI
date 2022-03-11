package baby_step.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Printer extends UnicastRemoteObject implements IPrinter {


    protected Printer(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void print(String line) throws RemoteException {
        System.out.println( "The Message is === " + line );
    }
}
