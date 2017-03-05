/**
 * Created by Home on 04/03/2017.
 */
import java.io.Serializable;

/*
    Abstrakte Klasse der Nachrichten
    Enthält zwei wichtige Datentypen.



 */
public abstract class AbstactMessage implements Serializable {

    Integer Int1;
    String Message;

    public String getMessage() {
        return Message;
    }

    public Integer getInt1() {
        return Int1;
    }
    public void setInt1(Integer anInt) {
        Int1 = anInt;
    }

    public void setMessage(String message) {
        Message = message;
    }


}