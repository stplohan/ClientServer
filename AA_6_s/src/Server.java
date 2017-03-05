/**
 * Created by Home on 04/03/2017.
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public void start() {
//        while (true) {
            try {
                ServerSocket sersoc = new ServerSocket(1234);
                System.out.println("Server wird gestarten...");
                while (true){
                    Socket socket = sersoc.accept();                                                  //Auf Verbindungen wird gewartet
//                    System.out.println("Verbunden mit:" + socket.getLocalSocketAddress());
                    try{
                        ObjectInputStream lesen = new ObjectInputStream(socket.getInputStream());

                        Message message = (Message) lesen.readObject();
                        if (message.getMessage().equals("1")) {

                            double zufall = Math.round(Math.random()*100);
                            Thread.sleep(100);
                            System.out.println("getRandom():" + zufall);
                            message.setInt1(1);
                            message.setMessage(String.valueOf(zufall));
                        } else {

                            long Ant2 = System.currentTimeMillis();
                            Thread.sleep(1000);
                            System.out.println("Wert:" + Ant2);
                            message.setMessage(String.valueOf(Ant2));
                            message.setInt1(2);
                        }

                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(message);


                    } catch (Exception e) {

                        System.out.println("Fehler bei der Übertragung");
                        e.printStackTrace();

                    }

                }
            } catch (Exception e) {

                System.out.println("Fehler Server");
                e.printStackTrace();
//                break;

            }
        }
//        System.out.println("Server beendet");*/


    }


