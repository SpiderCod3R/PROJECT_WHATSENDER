package whatsender.application.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import whatsender.application.helpers.MessageInterface;

/**
 *
 * @author ALEXANDRE
 */
@Entity
public class Message implements Serializable, MessageInterface{
    
    public static String datePattern = "MM/dd/yyyy";
    public static String hourPattern = "HH:mm";
    public static String DEFAULT_MESSAGE = "Olá ___________ , sua consulta/procedimento está marcada para o dia __/__/___, às  _:_. "
                                            + "\n Lembre-se que em caso de desistência, avise-nos com antecedência. "
                                            + "\n Se houver qualquer dúvida, entre em contato pelos telefones:";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
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

    @Override
    public String loadDefaultMessage() {
         return DEFAULT_MESSAGE;
    }

    @Override
    public String prepareMessage(Contact contact, Client client) {
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_MESSAGE.trim());
        stringBuilder.replace(70, 79, contact.getAppointment_date());
        stringBuilder.replace(85, 89, contact.getAppointment_hour());
        stringBuilder.replace(4, 15, contact.getName());
        
        if (!client.isNull()){
            stringBuilder.insert(stringBuilder.toString().trim().length(), client.toString());
        }
        
        return stringBuilder.toString();
    }

    @Override
    public String addClientDataToBodyMessage(Client clientData) {
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_MESSAGE.trim());
        stringBuilder.insert(DEFAULT_MESSAGE.trim().length(), clientData.toString());
        return stringBuilder.toString();
    }
}