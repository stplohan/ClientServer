/**
 * Created by Home on 04/03/2017.
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    ExecutorService executor = Executors.newCachedThreadPool();
    boolean p = true;
    Socket socket;
    public void start() {

        try {
            ServerSocket listener = new ServerSocket(4242);

            System.out.println("Server gestartet");
            while (p) {
                socket = listener.accept();                                                  //Auf Verbindungen wird gewartet
                System.out.println("verbunden mit:" + socket.getLocalSocketAddress());
                executor.execute(new ServerThread(socket, this));

            }
        } catch (Exception e) {

            System.out.println("Fehler Server");
            e.printStackTrace();


        }

        System.out.println("Server beendet");

    }


    public void Shutdown(){
        p=false;
        try {
            socket.close();
            executor.shutdown();
            executor.shutdownNow();

        }catch (Exception e) {
            System.out.println("Server beendet mit Fehler");
        }
    }



}
