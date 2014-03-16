package modele.node;

import java.util.Date;

/**
 *
 * @author Bruno
 */
public class Message implements Information {
    private Information content;
    private Date date;
    private int idSender;
    private boolean relevant;
    private AppVector vector;
    
    public Message(Information content, int idSender, boolean relevant, AppVector vector) {
        this.content = content;
        this.idSender = idSender;
        this.date = new Date();
        this.relevant = relevant;
        this.vector = vector;
    }
}
