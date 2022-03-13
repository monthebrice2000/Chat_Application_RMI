package chat_server.multiple_chat_rooms;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IParticipant extends Remote {

    public String name() throws RemoteException;
    public void receive(String name, String msg) throws RemoteException;

}
