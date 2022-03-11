package baby_step.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main( String[] argv ) throws RemoteException, AlreadyBoundException {

        IPrinter printer = new Printer(3001);
        Registry registry = LocateRegistry.createRegistry( 3000);
        System.out.println("++++++++++ Server looking for another invocation ++++++++++");
        registry.rebind("ip", printer);

    }
}
