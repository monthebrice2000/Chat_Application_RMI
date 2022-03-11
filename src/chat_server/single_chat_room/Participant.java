package chat_server.single_chat_room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Participant extends UnicastRemoteObject implements IParticipant {

    private String participantName = null;

    public Participant(int port, String name ) throws RemoteException {
        super(port);
        this.participantName = name;
    }

    @Override
    public String name() throws RemoteException{
        return this.participantName;
    }

    @Override
    public void receive(String name, String msg) throws RemoteException{
        System.out.println("Participant "+ name + " to " + this.participantName + " : " + msg );
    }
}
