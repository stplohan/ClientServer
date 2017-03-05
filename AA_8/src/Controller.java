/**
 * Created by Home on 17/05/2016.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {


    Model model = new Model();

    @FXML
    public Button connect;
    @FXML
    public Button send;
    @FXML
    public TextField IP;
    @FXML
    public TextField PORT;
    @FXML
    public TextField textToSendField;
    @FXML
    public Label status;
    @FXML
    public TextArea textView;
    @FXML
    public Button refresh;
    @FXML
    public TextField NAMEOFCLIENT;

    int port;
    String ipAdresse;
    String textToSend;
    Thread t1;
    String name;
    Thread onlineThread;



    public void onButtonclick(ActionEvent e){
        if(e.getSource().equals(connect)){
            port = Integer.parseInt(PORT.getText());
            ipAdresse = IP.getText();
            name = NAMEOFCLIENT.getText();
            model.setIpAdresse(ipAdresse);
            model.setPort(port);
            model.connectWithServer();
            t1 = new ReadThread(model.client, textView);
            onlineThread = new OnlineThread(name);
            onlineThread.start();
            t1.start();
            status.setText("Connected");
        }
        if(e.getSource().equals(send)){
            textToSend = textToSendField.getText();
            model.send(name+": "+textToSend);
        }
        if(e.getSource().equals(refresh)){
            String x = model.reader();
            textView.appendText(x);
        }
    }




}
