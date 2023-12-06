package whatsender.application.bot.models;
import whatsender.application.bot.config.boot.SystemFile;
import static whatsender.application.bot.config.boot.SystemFile.getFilePath;
import whatsender.application.bot.config.helpers.FormatterHelper;
import java.io.File;
import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

@XmlRootElement
// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    private SystemFile sysFile;
    
    @XmlAttribute
    private int id;

    @XmlElement(name = "cliente-nome")
    private String companyName = "";
    @XmlElement(name = "cliente-telefone")
    private String phoneNumber = "";
    @XmlElement(name = "cliente-whatsapp")
    private String whatsAppNumber = "";

    public Company(){
    }
    
    public Company(String companyName, String phoneNumber, String whatsAppNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.whatsAppNumber = whatsAppNumber;
    }
    
    public boolean save() throws IOException{
        String FILEPATH = getFilePath() + "\\GNSWhatSender\\data\\cliente.xml";
        
        try{
            JAXBContext jaxbContext = null;
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                        .createContext(new Class[]{Company.class}, null);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(this, new File(FILEPATH));
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the whatsAppNumber
     */
    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    /**
     * @param whatsAppNumber the whatsAppNumber to set
     */
    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }
    
    public void prepareDataToSendMessage(String cName, String phNumber, String whappNumber) {
        this.setCompanyName(cName);
        this.setPhoneNumber(phNumber);
        this.setWhatsAppNumber(whappNumber);
    }
    
    public Boolean isNotNull(){
        return !(("".equals(getCompanyName())) || ("".equals(FormatterHelper.formatPhoneNumber(getPhoneNumber()))) || ("".equals(FormatterHelper.formatPhoneNumber(getWhatsAppNumber()))));
    }
    
    public Boolean isNull(){
        return (("".equals(getCompanyName())) || ("".equals(FormatterHelper.formatPhoneNumber(getPhoneNumber()))) || ("".equals(FormatterHelper.formatPhoneNumber(getWhatsAppNumber()))));
    }

    @Override
    public String toString() {
        return "\n" + phoneNumber + " e " + whatsAppNumber + "\n" + companyName;
    }
    
    
    
    
}
