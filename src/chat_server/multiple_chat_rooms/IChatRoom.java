package chat_server.multiple_chat_rooms;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatRoom extends Remote {

    public String name() throws RemoteException;
    public void connect(IParticipant p) throws RemoteException;
    public void leave(IParticipant p) throws RemoteException;
    public String[] who() throws RemoteException;
    public void send(IParticipant p, String msg) throws RemoteException;

}
