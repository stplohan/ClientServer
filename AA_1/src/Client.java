/**
 * Created by stplohan on 16.01.2017.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{

    public static void main(String[] args) {

        int port = 1234;
        String Port = "";
        String IP = "";
        String UsrNm = "";

        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Verbindung zum Server...");


            System.out.print("\nLäuft der Server auf dem Pc, Entertaste drücken oder IP des Servers eingeben:");
            IP = inp.readLine();
            if (IP.equals(""))
                IP = "127.0.0.1";


            System.out.print("Port Nummer eingeben oder Entertaste drücken: ");
            Port = inp.readLine();
            if (Port.equals(""))
                port = 1234;
            else
                port = Integer.parseInt(Port);


            UsrNm = "";
            do {
                System.out.print("Benutzernamen eingeben: ");
                UsrNm = inp.readLine();
            }
            while (UsrNm.equals(""));

            Socket socket = new Socket(IP, port);
            ObjectOutputStream senden = new ObjectOutputStream(socket.getOutputStream());
            senden.writeObject(UsrNm + "\n");

            System.out.print("\n\n\t\tVerbindung erfolgreich!\n\t\t");

            int ergebnis =0;
            int zahl_1 = 0;
            int zahl_2 = 0;
            String zeichen ="";

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.print("Geben Sie bitte die erste Zahl ein:");
            String eingabe_1 = br.readLine();
            System.out.println("--------------------------------------");
            System.out.println("Du hast " + eingabe_1 + " eingegeben.");
            System.out.println("--------------------------------------");


            System.out.print("Geben Sie bitte die zweite Zahl ein:");
            String eingabe_2 = br.readLine();
            System.out.println("--------------------------------------");
            System.out.println("Du hast " + eingabe_2 + " eingegeben.");
            System.out.println("--------------------------------------");


            System.out.print("Geben Sie bitte die Rechenoperation ein:");
            String eingabe_3 = br.readLine();
            System.out.println("--------------------------------------");
            System.out.println("Du hast " + eingabe_3 + " eingegeben.");
            System.out.println("--------------------------------------");

            System.out.println("--------------------------------------");
            System.out.println("----Ergebnis findet Ihr beim Server!----");
            System.out.println("--------------------------------------");





            senden.writeObject(eingabe_1 + "\n" + eingabe_2 + "\n" + eingabe_3);

            socket.close();


        } catch (Exception e) {
            System.out.println("Ein Fehler ist aufgetreten!");

            System.exit(0);
        }

    }
}
