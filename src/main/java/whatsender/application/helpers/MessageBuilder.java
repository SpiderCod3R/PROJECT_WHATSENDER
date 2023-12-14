package whatsender.application.helpers;

import whatsender.application.entities.Appointment;
import whatsender.application.entities.Contact;
import whatsender.application.entities.Client;
import whatsender.application.entities.Message;
import whatsender.application.interfaces.MessageInterface;



public class MessageBuilder implements MessageInterface {
    public static String datePattern = "MM/dd/yyyy";
    public static String hourPattern = "HH:mm";
    public static String DEFAULT_MESSAGE = "Olá, [NOME_DO_PACIENTE], a sua consulta/procedimento está marcada para [DIA], [DATA] às [HORA],\n" +
                                            "com Dr/Dra [NOME_DO_RESPONSAVEL]. \n" +
                                            " Lembre-se que em caso de desistência, avise-nos com antecedência. \n" +
                                            " Se houver qualquer dúvida, entre em contato pelos telefones:";
    

    @Override
    public String loadDefaultMessage(){
        return DEFAULT_MESSAGE;
    }
    
    
    public String AddContactToMessage(Message message, Appointment appointment){
        String newMessage;
        newMessage = message.getBodyMessage().
                replace("[NOME_DO_PACIENTE]", appointment.getContact().getName()).
                replace("[DATA]", appointment.getData()).
                replace("[HORA]", appointment.getHora()).
                replace("[NOME_DO_RESPONSAVEL]", appointment.getDoctor());
        
        
        
        return newMessage;
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
    public String addClientDataToBodyMessage(Client client) {
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_MESSAGE.trim());
        stringBuilder.insert(DEFAULT_MESSAGE.trim().length(), client.toString());
        return stringBuilder.toString();
    }
}
