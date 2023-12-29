package whatsender.application.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import whatsender.application.helpers.LogType;
import whatsender.application.helpers.MessageType;

/**
 *
 * @author ALEXANDRE
 */
@Entity
@Table(name = LogMessage.TABLE_NAME)
public class LogMessage implements Serializable  {
    public static final String TABLE_NAME= "tb_log_de_mensagem";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(columnDefinition = "TEXT", name = "mensagem")
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Appointment contactAppointment;
    
    @Column(name = "data")
    private String data;
    
    @Column(name = "hora")
    private String hour;
    
    @Column(name = "log_type")
    private LogType logType;
    
    @Column(name = "message_type")
    private MessageType messageType;
    
    public LogMessage(Integer id, String message, Appointment contactAppointment, LogType logType, MessageType messageType) {
        this.id = id;
        this.message = message;
        this.contactAppointment = contactAppointment;
        this.data = contactAppointment.getData();
        this.hour = contactAppointment.getHora();
        this.logType = logType;
        this.messageType = messageType;
    }

    public LogMessage() {
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    
    
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Appointment getContactAppointment() {
        return contactAppointment;
    }

    public void setContactAppointment(Appointment contactAppointment) {
        this.contactAppointment = contactAppointment;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
}
