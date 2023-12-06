package whatsender.application.bot.config.boot;



import whatsender.application.bot.models.Company;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public abstract class FileConfig extends SystemFile {
    private static final String FILEPATH = getFilePath() + "\\GNSWhatSender\\data\\";
    private static Company company;
    
    public static String setFile(String fileName){
        return FILEPATH + fileName;
    }
    
    public static Company ReadFile(String fileName) {
       JAXBContext jaxbContext = null;
 
       try {
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Company.class}, null);

            File file = new File(setFile(fileName));

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            company = (Company) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return company;
    }
    
    public static boolean VerifyConfiguration(String file) {
        return isFile_created(file);
    }
}
