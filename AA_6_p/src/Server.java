/**
 * Created by Home on 04/03/2017.
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    public void start() {

        while (true) {
            try {
                ServerSocket sersoc = new ServerSocket(1234);
                ExecutorService executor = Executors.newCachedThreadPool();

                System.out.println("Server wird gestartet...");
                while (true) {
                    Socket socket = sersoc.accept();                                                  //Auf Verbindungen wird gewartet
                    System.out.println("verbunden mit:" + socket.getLocalSocketAddress());
                    executor.execute(new ServerThread(socket, this));
                }
            } catch (Exception e) {

                System.out.println("Fehler Server");
                e.printStackTrace();
                break;

            }
        }
        System.out.println("Server beendet");


    }



}
