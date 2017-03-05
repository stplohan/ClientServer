/**
 * Created by Home on 04/03/2017.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread {
    private Socket verbindung;
    private Server server;

    public ServerThread(Socket verbinden, Server server) throws IOException {          //Mit Construktor werden Verbindung und Server übergeben

        this.verbindung = verbinden;
        this.server = server;

    }

    @Override
    public void run() {
        try {

            ObjectInputStream in = new ObjectInputStream(verbindung.getInputStream());

            Message message = (Message) in.readObject();
            if (message.getMessage().equals("1")) {

                double zufall = Math.round(Math.random()*100);
                Thread.sleep(100);
                System.out.println("Wert:" + zufall);
                message.setInt1(1);

                message.setMessage(String.valueOf(zufall));
            } else {

                long zeit = System.currentTimeMillis();
                Thread.sleep(1000);
                System.out.println("Wert:" + zeit);
                message.setMessage(String.valueOf(zeit));
                message.setInt1(2);
            }

            ObjectOutputStream out = new ObjectOutputStream(verbindung.getOutputStream());
            out.writeObject(message);


        } catch (Exception e) {

            System.out.println("Fehler bei der Übertragung");
            e.printStackTrace();
        }finally {
            try {
                verbindung.close();
                System.out.println(" gesendet und getrennt");

            } catch (Exception e) {

                System.out.println("Fehler Server");
                e.printStackTrace();

            }
        }
    }


}





