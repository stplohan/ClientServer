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


        System.out.println("IP des Servers eingeben, auf dem die lange Wertberechnug auf Port 4242 laüft\n" +                  //IP des Servers wird eingelesen(Default127.0.0.1) oder exit befehl wird ausgeführt
                "(Default 127.0.0.1)Tippe 'exit' to exit");

        String serverAddress = "127.0.0.1";
        String enter = reader.readLine();
        if (enter.equals("exit")) {
            System.out.println("Programm beendet");
            System.exit(0);
        } else if (enter != null) {
            serverAddress = enter;
        }


        while (true) {

            System.out.println("Für getRandom()[1] oder für getTime()[2]");
            enter = reader.readLine();

                try {
                    message.setMessage(enter);
                    Socket s = new Socket(serverAddress, 1234);                                         //Verbindung wird aufgebaut und Pfad gesendet
                    Message a = new Message();
                    a.setMessage(enter);


                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(a);

                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());

                    a = (Message) in.readObject();


                    if(a.getInt1()==2){
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

}



