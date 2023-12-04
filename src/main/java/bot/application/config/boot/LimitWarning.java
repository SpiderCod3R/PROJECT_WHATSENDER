package bot.application.config.boot;

import static bot.application.config.boot.FileConfig.setFile;
import static bot.application.config.boot.SystemFile.getFilePath;
import bot.application.config.logs.SystemLog;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author ALEXANDRE
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LimitWarning {
    private LimitWarning limitWarning;

    public LimitWarning() {}
    public LimitWarning(LocalDate data_envio, LocalDateTime hora_envio, String numero_enviado) {
        this.data_envio = data_envio;
        this.hora_envio = hora_envio;
        this.numero_enviado = numero_enviado;
        this.mes_envio = data_envio.getMonth().name();
        Locale.setDefault(new Locale("pt","BR"));
    }
    
    @XmlAttribute
    private int id;
    
    @XmlElement(name = "data_envio")
    private LocalDate data_envio; 

    @XmlElement(name = "hora_envio")
    private LocalDateTime hora_envio; 
    
    @XmlElement(name = "mes_envio")
    private String mes_envio;
    
    @XmlElement(name = "numero_enviado")
    private String numero_enviado;
    
    @XmlElement(name = "status_contador")
    private String statusContador;
    
    @XmlElement(name = "contador_de_mensagens")
    private String contador_de_mensagens;
    
    private static final Integer MESSAGE_COUNTER = 0;
    private static final Integer MESSAGE_LIMIT = 1000;
    private DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static SystemLog systemLog;
    
    private void readAlertFile(){
        
        if (FileConfig.VerifyConfiguration("alerta.xml")){
            //limitWarning = FileConfig.ReadFile("alerta.xml");
        } else{
            systemLog = new SystemLog();
            systemLog.generateMessageLog("Erro", "O sistema não pode encontrar o arquivo especificado (cliente.xml)", null, "Ler Arquivo de Configuração");
        }
    }
    
    public boolean save() throws IOException{
        String FILEPATH = getFilePath() + "\\GNSWhatSender\\data\\alerta.xml";
        
        try{
            JAXBContext jaxbContext = null;
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                        .createContext(new Class[]{LimitWarning.class}, null);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(this, new File(FILEPATH));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    private LimitWarning ReadFile(String fileName) {
       JAXBContext jaxbContext = null;
       limitWarning = new LimitWarning();
       try {
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{LimitWarning.class}, null);

            File file = new File(setFile(fileName));

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            limitWarning = (LimitWarning) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return limitWarning;
    }

}
