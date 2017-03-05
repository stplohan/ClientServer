/**
 * Created by Home on 17/05/2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by stgarmic on 04.05.2016.
 */
public class Model {

    String ipAdresse;
    int port;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket client;



    public void connectWithServer(){
        try {
            client = new Socket("localhost",6666);
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            printWriter = new PrintWriter(client.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIpAdresse(String ipAdresse){
        this.ipAdresse = ipAdresse;
    }
    public void setPort(int port){
        this.port = port;
    }
    public void send(String textToSend){
        printWriter.println(textToSend);
        System.out.println(textToSend);
    }
    public String reader(){
        String xy = null;
        try {
            xy = bufferedReader.readLine();
            xy = xy+"\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xy;
    }

}

