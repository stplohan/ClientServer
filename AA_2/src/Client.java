/**
 * Created by Home on 02/03/2017.
 */
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        int port = 1234;
        String Port = "";
        String IP = "";
        String UsrNm = "";
        Auth a = new Auth();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Verbindung zum Server...");
        System.out.print("\nLäuft der Server auf dem Pc, Entertaste drücken oder IP des Servers eingeben:");
        IP = in.readLine();
        if (IP.equals(""))
            IP = "127.0.0.1";
        System.out.print("Port Nummer eingeben oder Entertaste drücken: ");
        Port = in.readLine();
        if (Port.equals(""))
            port = 1234;
        else
            port = Integer.parseInt(Port);

        UsrNm = "";
        do {
            System.out.print("Benutzernamen eingeben: ");
            UsrNm = in.readLine();
        }
        while (UsrNm.equals(""));
        a.setMessage(UsrNm);

        Socket socket = new Socket(IP, port);
        ObjectOutputStream senden = new ObjectOutputStream(socket.getOutputStream());
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
                    senden.writeObject(a);
                    int x = input.read();

        boolean aut = false; //Boolean aut = Authentifiziert

        if (x == 1) {
            aut = true;
            System.out.println("Authentifiziert!");

            int ergebnis = 0;
            int zahl_1 = 0;
            int zahl_2 = 0;
            String zeichen = "";

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

            }else{
                System.out.println("nicht Authentifiziert! \n");
                System.exit(0);
            }
        }

    }

