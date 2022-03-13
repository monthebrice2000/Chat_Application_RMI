package chat_server.multiple_chat_rooms;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    static Registry registry ;

    public static void main( String[] argv ) throws RemoteException {

        if( argv.length < 6){
            throw new RemoteException("Please Enter a valid arguments like : -h localhost -p 3003 -cr chatroom_name_1 chatroom_name_2 chatroom_name_3");
        }
        IChatRoom chatRoom1 = new ChatRoom(Integer.parseInt(argv[3]), argv[5] );
        IChatRoom chatRoom2 = new ChatRoom(Integer.parseInt(argv[3]), argv[6] );
        IChatRoom chatRoom3 = new ChatRoom(Integer.parseInt(argv[3]), argv[7] );
        registry = LocateRegistry.createRegistry(Integer.parseInt(argv[3]));
        registry.rebind("cr1", chatRoom1 );
        registry.rebind("cr2", chatRoom2 );
        registry.rebind("cr3", chatRoom3 );
        System.out.println("+++++++++ Server is looking for another invocation +++++++++");

    }
}
