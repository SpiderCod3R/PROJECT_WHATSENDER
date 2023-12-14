package whatsender.application.interfaces;

import java.text.ParseException;
import whatsender.application.entities.Appointment;
import whatsender.application.entities.Client;
import whatsender.application.entities.Message;

/**
 *
 * @author ALEXANDRE
 */
public interface MessageInterface {
    public String loadDefaultMessage();
    
    public String AddContactToMessage(Message message, Appointment appointment) throws ParseException;
    
    public String addClientDataToBodyMessage(String message, Client clientData);
}