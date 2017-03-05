/**
 * Created by Home on 03/03/2017.
 */
import java.io.*;
import java.net.Socket;

public class Client{
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

        System.out.print("Port Nummer eingeben oder Entertaste drücken: ");
            Port = in.readLine();
            if (Port.equals(""))
                port = 1234;
            else
                port = Integer.parseInt(Port);

            UsrNm = "";
            do {
                System.out.print("Benutzernamen eingeben: ");
                UsrNm = in.readLine();}
            while (UsrNm.equals(""));

            Socket socket = new Socket(IP, port);
            ObjectOutputStream senden = new ObjectOutputStream(socket.getOutputStream());
            senden.writeObject(UsrNm + "\n");

            System.out.print("\n\n\t\tVerbindung erfolgreich!\n\t\t");

            boolean x = false;
        while(!x) {
            System.out.println("Url eingeben mit https:// ");
            enter = in.readLine();
            url.setMessage(enter);
            senden.writeObject(url);
            senden.flush();

            InputStream input = socket.getInputStream();
            FileOutputStream fileOut = new FileOutputStream("Downloader.html");

            byte[] buffer = new byte[1024];//Vom Internet
            while (socket.isConnected()) {
            int bytesRead = input.read(buffer);
            if (bytesRead == -1) break;
                fileOut.write(buffer, 0, bytesRead);
            }

            fileOut.close();
            x=true;
        }
    }
}

