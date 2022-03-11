package baby_step.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinter extends Remote {

    public void print( String line ) throws RemoteException;
}
