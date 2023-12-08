package whatsender.application.helpers;

import whatsender.application.entities.Client;
import whatsender.application.entities.Contact;

/**
 *
 * @author ALEXANDRE
 */
public interface MessageInterface {
    public String loadDefaultMessage();
    
    public String prepareMessage(Contact contact, Client client);
    
    public String addClientDataToBodyMessage(Client clientData);
}