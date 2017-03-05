/**
 * Created by Home on 04/03/2017.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread {
    private Socket connection;
    private Server server;

    public ServerThread(Socket con, Server s) throws IOException {          //Mit Construktor werden Verbindung und Server übergeben

        this.connection = con;
        this.server = s;

    }

    @Override
    public void run() {
        try {

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

            Message M = (Message) in.readObject();
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            if (M.getMessage().equals("0")) {

                double Ant = Math.random()*99*(Math.random())*100;
                Thread.sleep(1000);                                 // Lange Berechnung simuliert
                System.out.println("Wert:" + Ant);
                M.setInt1(0);

                M.setMessage(String.valueOf(Ant));
                out = new ObjectOutputStream(connection.getOutputStream());
                out.writeObject(M);

            } if (M.getMessage().equals("shutdown")) {

                System.out.println("Server wird heruntergefahren");
                server.Shutdown();

            } if (M.getMessage().equals("1")) {

                long Ant2 = System.currentTimeMillis();
                Thread.sleep(1000);                                 // Lange Berechnung simuliert
                System.out.println("Wert:" + Ant2);
                M.setMessage(String.valueOf(Ant2));
                M.setInt1(1);

                out = new ObjectOutputStream(connection.getOutputStream());
                out.writeObject(M);
            }




        } catch (Exception e) {

            System.out.println("Fehler bei der Übertragung");
            e.printStackTrace();
        }finally {

            try {
                connection.close();
                System.out.println("gesendet und getrennt");

            } catch (Exception e) {

                System.out.println("Fehler Server");
                e.printStackTrace();

            }
        }
    }


}






