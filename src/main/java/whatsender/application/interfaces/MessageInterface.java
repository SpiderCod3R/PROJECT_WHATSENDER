package whatsender.application.interfaces;

import java.text.ParseException;
import whatsender.application.entities.Consulta;
import whatsender.application.entities.Cliente;
import whatsender.application.entities.Message;

/**
 *
 * @author ALEXANDRE
 */
public interface MessageInterface {
    public String loadDefaultMessage();
    
    public String AddContactToMessage(String message, Consulta appointment) throws ParseException;
    
    public String addClientDataToBodyMessage(String message, Cliente clientData);
}