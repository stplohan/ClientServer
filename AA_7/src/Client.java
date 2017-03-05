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
        Message M = new Message();


        System.out.println(  "IP des Servers eingeben, auf dem die lange Wertberechnug mit Shutdownfunktion auf Port 4242 laüft\n" +                  //IP des Servers wird eingelesen(Default127.0.0.1) oder exit befehl wird ausgeführt
                "(Default 127.0.0.1)Tippe 'exit' to exit");                                       //IP des Servers wird eingelesen(Default127.0.0.1) oder exit befehl wird ausgeführt


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


            System.out.println("Radom Wert(0) oder getTime(1)?(oder exit oder shutdown)");                 //Pfad eingeben oder exit
            enter = reader.readLine();
            if (enter.equals("exit")) {
                exit = true;
                break;
            }if (enter.equals("shutdown")) {
                exit = true;
                M.setMessage("shutdown");
                Socket s = new Socket(serverAddress, 4242);                                         //Verbindung wird aufgebaut und Pfad gesendet
                Message a = new Message();
                a.setMessage(enter);
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(a);
                out.flush();

            }else if (!enter.equals("shutdown")&& enter != null) {

                try {
                    M.setMessage(enter);
                    Socket s = new Socket(serverAddress, 4242);                                         //Verbindung wird aufgebaut und Pfad gesendet
                    Message a = new Message();
                    a.setMessage(enter);


                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(a);
                    out.flush();
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());

                    a = (Message) in.readObject();


                    if(a.getInt1()==1){
                        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");

                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(Long.parseLong(a.getMessage()));
                        System.out.println("Wert " + formatter.format(cal.getTime()));

                    }else{

                        System.out.println("Wert " + a.getMessage());
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

