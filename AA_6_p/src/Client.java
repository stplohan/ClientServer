/**
 * Created by Home on 04/03/2017.
 */
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client {


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Message message = new Message();


        System.out.println("P des Servers eingeben, auf dem die lange Wertberechnug auf Port 4242 laüft\n" +                  //IP des Servers wird eingelesen(Default127.0.0.1) oder exit befehl wird ausgeführt
                "(Default 127.0.0.1)Tippe 'exit' to exit");

        String serverAddress = "127.0.0.1";
        String enter = reader.readLine();
        if (enter.equals("exit")) {
            System.out.println("Programm beendet");
            System.exit(0);
        } else if (enter != null) {
            serverAddress = enter;
        }

        boolean exit = false;
        while (!exit) {


            System.out.println("getRandom()[1] oder getTime()[2]");                 //Pfad eingeben oder exit
            enter = reader.readLine();
            if (enter.equals("exit")) {
                exit = true;
            } else if (enter != null) {

                try {
                    message.setMessage(enter);
                    Socket socket = new Socket(serverAddress, 1234);                                         //Verbindung wird aufgebaut und Pfad gesendet
                    Message nachricht = new Message();
                    nachricht.setMessage(enter);


                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(nachricht);

                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    nachricht = (Message) in.readObject();


                    if(nachricht.getInt1()==2){
                        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");

                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(Long.parseLong(nachricht.getMessage()));
                        System.out.println("Wert " + formatter.format(cal.getTime()));

                    }else{

                        System.out.println("Wert " + nachricht.getMessage());
                    }


                } catch (Exception e) {

                    System.out.println("Fehler bei der Übertragung");
                    e.printStackTrace();

                }
            }
        }
        System.out.println("Programm beendet");
    }
}
