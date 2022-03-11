package chat_server.single_chat_room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatRoom extends UnicastRemoteObject implements IChatRoom {

    private String chatName = null;
    private String[] participants = new String[12];
    private int nextParticipant = 0;

    protected ChatRoom(int port, String chatName) throws RemoteException {
        super(port);
        this.chatName = chatName;
    }


    @Override
    public String name() throws RemoteException{
        //System.out.println("Chat Room Name : "+ this.chatName);
        return this.chatName;
    }

    @Override
    public IParticipant connect(String p) throws RemoteException{
        if( !isParticipantHere(p)){
            this.participants[nextParticipant++] = p.trim();
            System.out.println( "Participant Named "+ p + " is connected."  );
            return new Participant(3003, p);
        }else{
            System.out.println( "Participant Named "+ p + " is already here."  );
        }
        return null;
    }

    private boolean isParticipantHere(String participantName){
        for( int i = 0; i < nextParticipant; i++ ){
            if( participants[i].toString().equals(participantName) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public void leave(IParticipant p) throws RemoteException{
        int remove = -1;
        for( int i = 0; i < nextParticipant ; i++ ){
            if ( this.participants[i].toString().equals( p.name() )  ){
                remove = i;
            }
        }
        if( remove != -1 ){
            for( int i = remove; i < nextParticipant ; i++){
                this.participants[i] = this.participants[i+1];
            }
            this.nextParticipant --;
            remove = -1;
        }
        System.out.println( "Participant Name "+ p.name() + " leaves the chat.");
    }

    @Override
    public String[] who() throws RemoteException{
        String[] connectedUsers = new String[nextParticipant];
        for( int i = 0; i < connectedUsers.length ; i++ ){
            connectedUsers[i] = participants[i];
        }
        return connectedUsers;
    }

    @Override
    public void send(IParticipant p, String msg) throws RemoteException {
        if( isParticipantHere( p.name().trim() ) ){
            System.out.println("Participant " + p.name() + "sent >>> " + msg );
        }else{
            System.out.println( "Participant " + p.name() + " may be connected before send a message" );
        }
    }
}
