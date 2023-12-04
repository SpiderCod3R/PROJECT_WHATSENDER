package bot.application.config.logs;

import bot.application.config.boot.FileConfig;
import bot.application.config.boot.SystemFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;   
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALEXANDRE
 */
public class SystemLog extends SystemFile {
    
    private static File file;
    private static FileConfig fileConfig;
    private static String FILEPATH;
    private static String typeLog;
    
    private static ErrorLogs errorLogs;
    private static MessageLogs messageLogs;
    
    private LocalDate ldate = LocalDate.now();
    private LocalDateTime lTime = LocalDateTime.now();
    
    private DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");  

    private String FILENAME;
    private static final Logger LOG = Logger.getLogger(SystemLog.class.getName());
    
    public SystemLog() {
        this.FILEPATH = getFilePath() + "\\GNSWhatSender\\data\\";
        this.file = new File( this.FILEPATH);
        this.FILENAME = ldate.toString().replace("-", ""); 
    }
    
    public Integer incrementLogId(MessageLogs messageLogs){
        Integer id = 0;
        for (Log log : messageLogs.logs) {
            id = log.getLogId() + 1;
        }
        return id;
    }
    
    public void generateMessageLog(String typeLog, String messageLog, String whappNumber, String eventAction){
        Integer id = 1;
        ldate = LocalDate.now();
        lTime = LocalDateTime.now();
        String fileName = this.FILENAME.concat("." + typeLog.toLowerCase().concat(".log.xml"));
        String fileLocation = file.getAbsolutePath() + "\\" + fileName;
        messageLogs = new MessageLogs();
        
        if (whappNumber == null){
            whappNumber = "-";
        }
        
        if (isFile_created(fileName)) {
            readLog("." + typeLog.toLowerCase());
            for (Log log : messageLogs.logs) {
               id = log.getLogId() + 1;
            }
            messageLogs.logs.add(new Log(
                id,
                typeLog, 
                messageLog,
                    whappNumber, 
                eventAction, 
                ldate.format(dtfData), 
                lTime.format(dtfHora)));
        } else {
            messageLogs.logs.add(new Log(
                id,
                typeLog, 
                messageLog, 
                    whappNumber,
                eventAction, 
                ldate.format(dtfData), 
                lTime.format(dtfHora)));
        }
        
        /*for (Log log : messageLogs.logs) {
            System.out.println(log.toString());
        }*/
        
        try{
            JAXBContext jaxbContext = null;
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                        .createContext(new Class[]{MessageLogs.class}, null);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(messageLogs, new File(fileLocation));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    
    public Boolean readLog(String log){
        JAXBContext jaxbContext = null;
        try {
            this.typeLog = log;
            File file = new File(fileConfig.setFile(this.FILENAME.concat(log.concat(".log.xml"))));
            if(file.exists()){
                jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{MessageLogs.class}, null);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                messageLogs = (MessageLogs) jaxbUnmarshaller.unmarshal(file);
            } else {
                return false;
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static List<Log> getLogsList() {
        return messageLogs.logs;
    }
    
    public static Integer getLogsListLenght() {
        return messageLogs.logs.size();
    }
    
    public static ErrorLogs getErrorLogs() {
        return errorLogs;
    }

    public static void setErrorLogs(ErrorLogs errorLogs) {
        SystemLog.errorLogs = errorLogs;
    }

    public static String getTypeLog() {
        return typeLog;
    }

    public static void setTypeLog(String typeLog) {
        SystemLog.typeLog = typeLog;
    }
    
    

    
}
