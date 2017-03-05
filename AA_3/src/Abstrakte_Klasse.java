/**
 * Created by Home on 05/03/2017.
 */
import java.io.Serializable;

/*
    Abstrakte Klasse
    Dient zum Zwischenspeichern
    Tipp von Klassenkolegen
    Kopiert von anderen Arbetsaufträgen
 */
public abstract class Abstrakte_Klasse implements Serializable {

    int zs1; // zs = Zwischenspeicher
    String Message;

    public String getMessage() {
        return Message;
    }

    public Integer getzs1() {
        return zs1;
    }
    public void setzs1(int Zs1) {
        zs1 = Zs1;
    }

    public void setMessage(String message) {
        Message = message;
    }
}

