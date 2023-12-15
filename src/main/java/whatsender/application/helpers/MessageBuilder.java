package whatsender.application.helpers;

import java.text.DateFormatSymbols;
import whatsender.application.entities.Appointment;
import whatsender.application.entities.Client;
import whatsender.application.entities.Message;
import whatsender.application.interfaces.MessageInterface;

import java.text.Format;  
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MessageBuilder implements MessageInterface {
    public static String datePattern = "dd/MM/yyyy";
    public static String hourPattern = "HH:mm";
    public static String DEFAULT_MESSAGE = "Olá, [NOME_DO_PACIENTE], a sua consulta/procedimento está marcada para [DATA] às [HORA],\n" +
                                            "com Dr/Dra [NOME_DO_RESPONSAVEL]. \n" +
                                            " Lembre-se que em caso de desistência, avise-nos com antecedência. \n" +
                                            " Se houver qualquer dúvida, entre em contato pelos telefones:";
    

    @Override
    public String loadDefaultMessage(){
        return DEFAULT_MESSAGE;
    }
    
    @Override
    public String AddContactToMessage(String message, Appointment appointment) throws ParseException{
        String newMessage;
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, new Locale ("pt", "BR"));
        Date date= sdf.parse(appointment.getData());
        sdf.applyPattern("EEE, d MMM yyyy");  
        String dataFormatada = sdf.format(date);  
        System.out.println("Data Formatada: "+dataFormatada);  
        
        newMessage = message.
            replace("[NOME_DO_PACIENTE]", appointment.getContactName()).
            replace("[DATA]", dataFormatada).
            replace("[HORA]", appointment.getHora()).
            replace("[NOME_DO_RESPONSAVEL]", appointment.getDoctor());

        return newMessage;
    }

    @Override
    public String addClientDataToBodyMessage(String message, Client client) {
        StringBuilder stringBuilder = new StringBuilder(message.trim());
        stringBuilder.insert(DEFAULT_MESSAGE.trim().length(), client.toString());
        return stringBuilder.toString();
    }
}
