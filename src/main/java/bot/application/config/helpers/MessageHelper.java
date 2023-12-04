package bot.application.config.helpers;

import bot.application.models.Client;
import bot.application.models.Company;



public abstract class MessageHelper {
    public static String datePattern = "MM/dd/yyyy";
    public static String hourPattern = "HH:mm";
    public static String DEFAULT_MESSAGE = "Olá, [NOME_DO_PACIENTE], a sua consulta/procedimento está marcada para [DIA], [DATA] às [HORA],\n" +
                                            "com Dr/Dra [NOME_DO_RESPONSAVEL]. \n" +
                                            " Lembre-se que em caso de desistência, avise-nos com antecedência. \n" +
                                            " Se houver qualquer dúvida, entre em contato pelos telefones:";
    
    public static String loadDefaultMessage(){
        return DEFAULT_MESSAGE;
    }
    
    public static String prepareMessage(Client client, Company company){
        /*SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        SimpleDateFormat hourFormat = new SimpleDateFormat(hourPattern);
        String dataDeAtendimento = dateFormat.format(client.getAppointment_date());
        String horaDeAtendimento = hourFormat.format(client.getAppointment_hour());
        */
        
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_MESSAGE.trim());
        stringBuilder.replace(70, 79, client.getAppointment_date());
        stringBuilder.replace(85, 89, client.getAppointment_hour());
        stringBuilder.replace(4, 15, client.getName());
        
        if (company.isNotNull()){
            stringBuilder.insert(stringBuilder.toString().trim().length(), company.toString());
        }
        
        return stringBuilder.toString();
    }
    
    public static String addCompanyDataToBodyMessage(Company companyData){
        StringBuilder stringBuilder = new StringBuilder(DEFAULT_MESSAGE.trim());
        stringBuilder.insert(DEFAULT_MESSAGE.trim().length(), companyData.toString());
        return stringBuilder.toString();
    }
}
