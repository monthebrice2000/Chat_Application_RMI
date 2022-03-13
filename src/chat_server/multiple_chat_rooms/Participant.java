package chat_server.multiple_chat_rooms;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Participant extends UnicastRemoteObject implements IParticipant, Serializable {

    private String participantName = null;

    public Participant(String name ) throws RemoteException {
        this.participantName = name;
    }

    @Override
    public String name() throws RemoteException{
        return this.participantName;
    }

    @Override
    public synchronized void receive(String name, String msg) throws RemoteException{
        if( msg.toString().contains( "is connected") || msg.toString().contains( "is disconnected") ){
            System.out.println( msg );
        }else{
            System.out.println("Participant "+ name + " to All : " + msg );
        }
    }
}
