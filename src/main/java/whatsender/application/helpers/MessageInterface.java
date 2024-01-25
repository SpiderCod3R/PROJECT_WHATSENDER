package whatsender.application.helpers;

import java.io.IOException;
import java.text.ParseException;
import whatsender.application.entities.Cliente;
import whatsender.application.entities.Consulta;

public interface MessageInterface {
    String loadDefaultMessage();
    String AddContactToMessage(String message, Consulta appointment) throws ParseException;
    String addClientDataToBodyMessage(String message, Cliente client);
}
