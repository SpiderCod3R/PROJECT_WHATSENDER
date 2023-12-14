package whatsender.application.entities;

import whatsender.application.helpers.FormatterHelper;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String clientName;
    private String phoneNumber;
    private String whatsApp;

    public Client(){
    }
    
    public Client(String clientName, String phoneNumber, String whatsApp) {
        super();
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.whatsApp = whatsApp;
    }

    public Client(Integer id, String clientName, String phoneNumber, String whatsApp) {
        this.id = id;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.whatsApp = whatsApp;
    }

    @Override
    public String toString() {
        return "\n" + phoneNumber + " e " + whatsApp + "\n" + clientName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isNull(){
        return (("".equals(getClientName())) || ("".equals(getWhatsApp())));
    }
}
