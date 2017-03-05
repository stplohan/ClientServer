/**
 * Created by Home on 17/05/2016.
 */
import java.io.IOException;
import java.net.*;

/**
 * Created by stgarmic on 11.05.2016.
 */
public class OnlineThread extends Thread {

    boolean x = true;
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    byte[] sendData;
    byte[] receiveData;
    String name;

    public OnlineThread(String name){
        try {
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("localhost");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        sendData = new byte[1024];
        receiveData = new byte[1024];
        this.name = name;
    }


    @Override
    public void run(){

        while (x){
            String sentence = name;
            sendData = sentence.getBytes();
            //---------------------Senden
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //---------------------Bekommen
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);

        }
    }



}
