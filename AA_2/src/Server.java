/**
 * Created by stbormir on 09.01.2017.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
class NoAuth extends Exception {

    NoAuth(){
        super("Nicht Authetifizierter Benutzer!");
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
            ServerSocket sersoc = new ServerSocket(1234);
            ArrayList<String> AuUser = new ArrayList<String>(); //AuUser -> Authentifizierte User

            AuUser.add("Hannes");                       //Authentifizierte User
            AuUser.add("Maximilian");
            AuUser.add("Ulrich");



        while (true) {
                Socket socket = sersoc.accept();
                try {
                    InputStream in = socket.getInputStream();

                    ObjectInputStream lesen = new ObjectInputStream(socket.getInputStream());
                    OutputStream out = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(out, true);
                    try {

                        Auth a = (Auth) lesen.readObject();                            //User wird empfangen
                        String Username = a.getMessage();
                        int i = 0;


                        while (AuUser.size() > i && AuUser.get(i) != null) {                    //User wird kontrolliert
                            if (Username.equals(AuUser.get(i))) {
                                System.out.println("Verbunden mit:" + AuUser.get(i));
                                i = Integer.MAX_VALUE;
                                out.write(1);
                                break;
                            }
                            i++;
                        }

                        if (i != Integer.MAX_VALUE) {
                            out.write(0);
                            throw new NoAuth();
                        }
                        out.flush();

                        int ergebnis = 0;
                        int zahl_1 = 0;
                        int zahl_2 = 0;
                        String zeichen = "";
                        String Eingabe = lesen.readObject().toString();
                        String[] split = Eingabe.split("\n");
                        zahl_1 = Integer.parseInt(split[0]);
                        zahl_2 = Integer.parseInt(split[1]);
                        zeichen = split[2];

                        switch (zeichen) {
                            case "+": {

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


                    } catch (java.lang.ClassNotFoundException e) {

                        System.out.println("Fehler!!s");

                    } catch (NoAuth e) {
                        System.out.println(e.getMessage());
                    }

                } finally {
                    socket.close();
                    System.out.println("Fertig berechnet");
                }
            }

        }

    }


