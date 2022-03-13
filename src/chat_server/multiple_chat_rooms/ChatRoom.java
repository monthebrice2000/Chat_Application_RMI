package chat_server.multiple_chat_rooms;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatRoom extends UnicastRemoteObject implements IChatRoom {

    private String chatName = null;
    private IParticipant[] participants = new IParticipant[12];
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
    public synchronized void connect(IParticipant p) throws RemoteException{
        if( !isParticipantHere( p.name().toString() )){
            this.participants[nextParticipant++] = p;
            displayWelcomeMsg( p, "is connected");
            System.out.println( "Participant Named "+ p.name().toString() + " is connected."  );
        }else{
            System.out.println( "Participant Named "+ p.name().toString() + " is already here."  );
        }
    }

    private void displayWelcomeMsg(IParticipant p, String welcomeMsg ) throws RemoteException {
        for( int i=0; i<nextParticipant; i++){
            if( p.name().toString().equals( this.participants[i].name().toString() ) )
                continue;
            this.participants[i].receive( p.name().toString(), "<<<<< Participant " + p.name().toString() + " is connected now >>>>>>");
        }
    }

    private boolean isParticipantHere(String participantName) throws RemoteException {
        for( int i = 0; i < nextParticipant; i++ ){
            if( participants[i].name().toString().equals(participantName) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void leave(IParticipant p) throws RemoteException{
        int remove = -1;
        for( int i = 0; i < nextParticipant ; i++ ){
            if ( this.participants[i].name().toString().equals( p.name().toString() )  ){
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
        sendGoMsg( p, "is disconnected now");
        System.out.println( "Participant Name "+ p.name().toString() + " leaves the chat.");
    }

    private void sendGoMsg(IParticipant p, String goMsg ) throws RemoteException {
        for( int i=0; i<nextParticipant; i++){
            if( p.name().toString().equals( this.participants[i].name().toString() ) )
                continue;
            this.participants[i].receive( p.name().toString(), "<<<<< Participant " + p.name().toString() + " is disconnected now >>>>>>");
        }
    }

    @Override
    public synchronized String[] who() throws RemoteException{
        String[] connectedUsers = new String[nextParticipant];
        for( int i = 0; i < connectedUsers.length ; i++ ){
            connectedUsers[i] = participants[i].name().toString();
        }
        return connectedUsers;
    }

    @Override
    public  synchronized void send(IParticipant p, String msg) throws RemoteException {
        if( isParticipantHere( p.name().trim() ) ){
            System.out.println("Participant " + p.name() + " sent >>> " + msg );
            sendMsg(p, msg);
        }else{
            System.out.println( "Participant " + p.name() + " may be connected before send a message" );
        }
    }

    private void sendMsg(IParticipant p, String anotherMsg ) throws RemoteException {
        for( int i=0; i<nextParticipant; i++){
            /*if( p.name().toString().equals( this.participants[i].name().toString() ) )
                continue;*/
            this.participants[i].receive( p.name().toString(), anotherMsg );
        }
    }
}
