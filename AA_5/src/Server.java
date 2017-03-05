/**
 * Created by Home on 04/03/2017.
*/
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ServerSocket sersoc;
    Socket socket = null;

    public void start(){

        while (true) {
            try {
                sersoc = new ServerSocket(1234);
                ExecutorService executor = Executors.newCachedThreadPool();             // Für Threads

                System.out.println("Server wird gestartet...");
                while (true) {
                    Socket socket = sersoc.accept();
                    System.out.println("verbunden mit:" + socket.getLocalSocketAddress());
                    executor.execute(new ServerThread(socket, this));
                }
            } catch (Exception e) {


                e.printStackTrace();


            }
        }

    }
}