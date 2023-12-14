package whatsender.application.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ALEXANDRE
 */
@Entity
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String bodyMessage;
    
    public Message() {}
    
    public Message(Integer id, String bodyMessage) {
        this.id = id;
        this.bodyMessage = bodyMessage;
    }
    
    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }
}