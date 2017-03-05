import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Home  on 04.02.2017.
 * Thread des Servers
 */

public class ServerThread extends Thread {
    private Socket verbindung;
    private Server server;
    BufferedReader in =null;
    PrintWriter out;

    public ServerThread(Socket verbinden, Server server){
        try {
            this.verbindung = verbinden;
            this.server = server;
            in = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
            out = new PrintWriter(verbindung.getOutputStream(), true);

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    @Override
    public void run() {
        try {


            ObjectInputStream in = new ObjectInputStream(verbindung.getInputStream());
            try {


                Url a = (Url) in.readObject();
                Path Link = (Paths.get(a.getMessage()));
                System.out.println(Link+" Wird gesendet");

                OutputStream out = verbindung.getOutputStream();
                InputStream fileIn = new FileInputStream(Link.toFile());

                byte[] buffer = new byte[1024];
                while (fileIn.available() > 0) {
                    out.write(buffer, 0, fileIn.read(buffer));
                }

                fileIn.close();




            } catch (Exception e) {

                System.out.println("Fehler!");
                e.printStackTrace();

            }


        }catch (Exception e) {

            System.out.println("Fehler Server");

        }finally {
            try{
                verbindung.close();

            } catch (Exception e) {

                System.out.println("Fehler beim Server");
                e.printStackTrace();

            }
        }
    }




}





