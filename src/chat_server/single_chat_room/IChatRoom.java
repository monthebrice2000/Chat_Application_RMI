package chat_server.single_chat_room;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatRoom extends Remote {

    public String name() throws RemoteException;
    public IParticipant connect(String p) throws RemoteException;
    public void leave(IParticipant p) throws RemoteException;
    public String[] who() throws RemoteException;
    public void send(IParticipant p, String msg) throws RemoteException;

}
