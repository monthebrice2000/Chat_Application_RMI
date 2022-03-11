package baby_step.client;

import baby_step.server.IPrinter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main( String[] argv ) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", 3000);
        IPrinter printer = ( IPrinter ) registry.lookup("ip");
        printer.print("Hello World");

    }
}
