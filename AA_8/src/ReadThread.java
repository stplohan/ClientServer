/**
 * Created by Home on 17/05/2016.
 */
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by stgarmic on 05.05.2016.
 */
public class ReadThread extends Thread {
    public Socket client;
    public BufferedReader bufferedReader;
    public TextArea textArea;


    public ReadThread(Socket client, TextArea textView){
        this.client = client;
        textArea = textView;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(true){
            try {
                String eingabe = bufferedReader.readLine();
                System.out.println(eingabe+"\n");
                textArea.appendText(eingabe+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
