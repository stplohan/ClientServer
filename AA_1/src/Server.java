/**
 * Created by stplohan on 16.01.2017.
 */
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String []args){
        try{
            ServerSocket sersoc = new ServerSocket(1234);

            while(true){
                Socket socket = sersoc.accept();

                InputStream in = socket.getInputStream();

                ObjectInputStream lesen = new ObjectInputStream(socket.getInputStream());
                OutputStream out = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(out, true);

                String Username = lesen.readObject().toString();
                System.out.println("Neuer Client verbunden:" + Username);


                int ergebnis = 0;
                int zahl_1 = 0;
                int zahl_2 = 0;
                String zeichen ="";
                String Eingabe = lesen.readObject().toString();
                String [] split = Eingabe.split("\n");
                zahl_1 = Integer.parseInt(split[0]);
                zahl_2 = Integer.parseInt(split[1]);
                zeichen = split[2];


                switch(zeichen){
                    case "+":{

                        ergebnis = zahl_1 + zahl_2;
                        System.out.println("Dein Ergebnis:" + ergebnis);
                        break;
                    }

                    case "-": {

                        ergebnis = zahl_1 - zahl_2;
                        System.out.println("Dein Ergebnis:" + ergebnis);
                        break;
                    }

                    case "*": {

                        ergebnis = zahl_1 * zahl_2;
                        System.out.println("Dein Ergebnis:" + ergebnis);
                        break;
                    }

                    case "/": {

                        ergebnis = zahl_1 / zahl_2;
                        System.out.println("Dein Ergebnis:" + ergebnis);
                        break;
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}