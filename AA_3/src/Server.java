/**
 * Created by Home on 03/03/2017.
 */
import java.io.*;

import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server{
    public static void main(String[] args) throws IOException {
        ServerSocket sersoc = new ServerSocket(1234);

            while (true){
                Socket socket = sersoc.accept();
                ObjectInputStream lesen = new ObjectInputStream(socket.getInputStream());
                String Username = null;
                try {
                    Username = lesen.readObject().toString();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Neuer Client verbunden:" + Username);
                    try{
                        Url url = (Url) lesen.readObject();
                        System.out.println("Webseite gefunden!");
                        WebDownloader downloader = new WebDownloader();
                        String ws = url.getMessage(); // ws -> Webseite
                        try {
                            downloader.saveTo(new URL(ws),"Server-exit.html");

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        OutputStream out = socket.getOutputStream();
                        InputStream fileIn = new FileInputStream("Server-exit.html");

                        byte[] buffer = new byte[1024];
                        while (fileIn.available() > 0) {
                            out.write(buffer, 0, fileIn.read(buffer));
                        }
                        fileIn.close();
                    } catch (Exception e) {

                        e.printStackTrace();
                        System.out.println("Fehler!!");

                    }
        }
    }
}
