package chat_server.multiple_chat_rooms;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    static Registry registry;

    public static void main( String[] argv ) throws RemoteException, NotBoundException {

        if( argv.length != 6){
            throw new RemoteException("Please Enter a valid arguments like : -h localhost -p 3003 -u brice");
        }
        registry = LocateRegistry.getRegistry(argv[1], Integer.parseInt(argv[3]));
        IChatRoom chatRoom = null;
        IParticipant participant = new Participant( argv[5].toString().trim().toUpperCase()  );
        registry.rebind("pt", participant);
        String response = null;
        boolean exit = false;
        boolean exit_from_chat = false;
        String responseChatName = null;
        String messageToSend = null;
        Scanner input = new Scanner( System.in );
        while( !exit ){
            System.out.println("Please Select The Chat Name or exit to quit: ");
            System.out.println("1 -> For the First Chat Room\n2 -> For the Second Chat Room\n3 -> For the Third Chat Room\nexit -> Quit ");
            responseChatName = input.nextLine().trim();
            switch ( responseChatName.toLowerCase() ){
                case "1" :
                    chatRoom = (IChatRoom) registry.lookup("cr1");
                    chatRoom.connect( participant );
                    System.out.println("You are connected to "+ chatRoom.name());
                    while( !exit_from_chat ){
                        System.out.println("Select one option below : ");
                        System.out.println("1 -> Type A Message \n2 -> Leave A Chat Room\n3 -> Get All Users Connected\n4 -> Get His Name \n5 -> Quit");

                        response = input.nextLine().trim();
                        switch ( response ){
                            case "1" :
                                System.out.println("Type A Message : ");
                                messageToSend = input.nextLine().trim();
                                chatRoom.send(participant, messageToSend);
                                break;
                            case "2" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                break;
                            case "3" :
                                String[] users = chatRoom.who( );
                                System.out.print( "( + "+ users.length + " ) Users Connected Are >>> " );
                                for( int i = 0; i<users.length ; i++ ){
                                    System.out.print( users[i] + "   ");
                                }
                                System.out.println("");
                                break;
                            case "5" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                exit = true;
                                break;
                            case "4" :
                                System.out.println("My Name is : " + participant.name() );
                                break;
                            default:
                                System.out.println("You write a wrong option, Retry!!!");
                        }
                    }
                    exit_from_chat = false;
                    break;
                case "2" :
                    chatRoom = (IChatRoom) registry.lookup("cr2");
                    chatRoom.connect( participant );
                    System.out.println("You are connected to "+ chatRoom.name());
                    while( !exit_from_chat ){
                        System.out.println("Select one option below : ");
                        System.out.println("1 -> Type A Message \n2 -> Leave A Chat Room\n3 -> Get All Users Connected\n4 -> Get His Name \n5 -> Quit");

                        response = input.nextLine().trim();
                        switch ( response ){
                            case "1" :
                                System.out.println("Type A Message : ");
                                messageToSend = input.nextLine().trim();
                                chatRoom.send(participant, messageToSend);
                                break;
                            case "2" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                break;
                            case "3" :
                                String[] users = chatRoom.who( );
                                System.out.print( "( + "+ users.length + " ) Users Connected Are >>> " );
                                for( int i = 0; i<users.length ; i++ ){
                                    System.out.print( users[i] + "   ");
                                }
                                System.out.println("");
                                break;
                            case "5" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                exit = true;
                                break;
                            case "4" :
                                System.out.println("My Name is : " + participant.name() );
                                break;
                            default:
                                System.out.println("You write a wrong option, Retry!!!");
                        }
                    }
                    exit_from_chat = false;
                    break;
                case "3" :
                    chatRoom = (IChatRoom) registry.lookup("cr3");
                    chatRoom.connect( participant );
                    System.out.println("You are connected to "+ chatRoom.name());
                    while( !exit_from_chat ){
                        System.out.println("Select one option below : ");
                        System.out.println("1 -> Type A Message \n2 -> Leave A Chat Room\n3 -> Get All Users Connected\n4 -> Get His Name \n5 -> Quit");

                        response = input.nextLine().trim();
                        switch ( response ){
                            case "1" :
                                System.out.println("Type A Message : ");
                                messageToSend = input.nextLine().trim();
                                chatRoom.send(participant, messageToSend);
                                break;
                            case "2" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                break;
                            case "3" :
                                String[] users = chatRoom.who( );
                                System.out.print( "( + "+ users.length + " ) Users Connected Are >>> " );
                                for( int i = 0; i<users.length ; i++ ){
                                    System.out.print( users[i] + "   ");
                                }
                                System.out.println("");
                                break;
                            case "5" :
                                chatRoom.leave( participant );
                                System.out.println("You are leaving the "+ chatRoom.name() + " Chat Room");
                                exit_from_chat = true;
                                exit = true;
                                break;
                            case "4" :
                                System.out.println("My Name is : " + participant.name() );
                                break;
                            default:
                                System.out.println("You write a wrong option, Retry!!!");
                        }
                    }
                    exit_from_chat = false;
                    break;
                case "exit" :
                    exit = true;
                    System.out.println("You are existing all the application");
                    break;
                default:
                    System.out.println("You write a wrong option, Retry!!!");
            }
        }


    }
}
