/**
 * Created by Home on 04/03/2017.
 */

import java.io.*;
import java.net.Socket;

import java.nio.file.Paths;

public class Client {


    public static void main(String[] args) throws IOException {
        Url url = new Url();
        int port = 1234;
        String Port = "";
        String IP = "";
        String UsrNm = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Verbindung zum Server...");

        System.out.print("\nLäuft der Server auf dem Pc, Entertaste drücken oder IP des Servers eingeben:");
            IP = in.readLine();
        if (IP.equals(""))
            IP = "127.0.0.1";
        String enter= in.readLine();

        System.out.print("Port Nummer: ");
        Port = in.readLine();
        if (Port.equals(""))
            port = 1234;
        else
            port = Integer.parseInt(Port);


        do {
            System.out.print("Benutzernamen eingeben: ");
            UsrNm = in.readLine();}
        while (UsrNm.equals(""));

        Socket socket = new Socket(IP, port);
        ObjectOutputStream senden = new ObjectOutputStream(socket.getOutputStream());


        System.out.print("\n\n\t\tVerbindung erfolgreich!\n\t\t");


        while(true){
            System.out.println("File mit Pfad eingeben (oder exit)");
            enter = in.readLine();


                url.setMessage(enter);
               Socket s = new Socket(IP, 1234);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(url);
                out.flush();

                String file = Paths.get(url.getMessage()).toString();

                String extension = "";

                int i = file.lastIndexOf('.');
                if (i > 0) {
                    extension = file.substring(i+1);
                }

                InputStream input = socket.getInputStream();
                FileOutputStream fileOut = new FileOutputStream("Downloader."+extension);

                byte[] buffer = new byte[1024];
                while (socket.isConnected()) {
                    int bytesRead = input.read(buffer);
                    if (bytesRead == -1) break;
                    fileOut.write(buffer, 0, bytesRead);
                }

                fileOut.close();

            }
        }

}
