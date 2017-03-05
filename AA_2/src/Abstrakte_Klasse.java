/**
 * Created by Home on 02/03/2017.
 */
import java.io.Serializable;

/*
    Abstrakte Klasse, um Daten zwischenzuspeichern
 */
public abstract class Abstrakte_Klasse implements Serializable {

    int zs1;
    String Message;

    public String getMessage() {
        return Message;
    }

    public int getZs1() {
        return zs1;
    }
    public void setZs1(int ZS1) {
        zs1 = ZS1;
    }

    public void setMessage(String message) {
        Message = message;
    }


}