package chat_server.single_chat_room;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main( String[] argv ) throws RemoteException {

        if( argv.length != 6){
            throw new RemoteException("Please Enter a valid arguments like : -h localhost -p 3003 -cr chatroom_name");
        }
        IChatRoom chatRoom = new ChatRoom(Integer.parseInt(argv[3]), argv[5] );
        //IChatRoom chatRoom = new ChatRoom(3003, "Polytech Info 4" );
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(argv[3]));
        registry.rebind("cr", chatRoom );
        System.out.println("+++++++++ Server is looking for another invocation +++++++++");

    }
}
