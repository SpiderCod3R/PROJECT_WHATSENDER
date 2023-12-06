package whatsender.application.bot.config.logs;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

/**
 *
 * @author ALEXANDRE
 */
@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log implements LogType{
    @XmlAttribute(name = "id")
    private Integer logId;

    @XmlElement(name = "log_type")
    private String log_type;
    
    @XmlCDATA
    @XmlElement(name = "message_log")
    private String message_log;
    
    @XmlElement(name = "event_action")
    private String event_action;
    
    @XmlElement(name = "data_log")
    private String data;
    
    @XmlElement(name = "hora_log")
    private String hora;
    
    @XmlElement(name = "whatsapp")
    private String whatsapp_number;

    public Log(Integer logId, String log_type, String message_log, String event_action, String data, String hora) {
        this.logId = logId;
        this.log_type = log_type;
        this.message_log = message_log;
        this.data = data;
        this.hora = hora;
        this.event_action = event_action;
    }
    
    public Log(Integer logId, String log_type, String message_log, String whappNumber, String event_action, String data, String hora) {
        this.logId = logId;
        this.log_type = log_type;
        this.message_log = message_log;
        this.data = data;
        this.hora = hora;
        this.event_action = event_action;
        this.whatsapp_number = whappNumber;
    }

    public Log() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.log_type);
        hash = 23 * hash + Objects.hashCode(this.message_log);
        hash = 23 * hash + Objects.hashCode(this.data);
        hash = 23 * hash + Objects.hashCode(this.hora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Log{" + "logId=" + logId + ", log_type=" + log_type + ", message_log=" + message_log + ", event_action=" + event_action + ", data=" + data + ", hora=" + hora + '}';
    }

    @Override
    public Integer getLogId() {
        return this.logId;
    }

    @Override
    public void setLogId(Integer value) {
        this.logId = value;
    }

    @Override
    public String getLogType() {
        return this.log_type;
    }

    @Override
    public void setLogType(String value) {
        this.log_type = value;
    }

    @Override
    public String getMessageLog() {
        return this.message_log;
    }

    @Override
    public void setMessageLog(String value) {
        this.message_log = value;
    }

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void setData(String value) {
        this.data = value;
    }

    @Override
    public String getHour() {
        return this.hora;
    }

    @Override
    public void setHour(String value) {
        this.hora =value;
    }

    public String getEventAction() {
        return event_action;
    }

    public String getWhatsapp_number() {
        return whatsapp_number;
    }

    public void setWhatsapp_number(String whatsapp_number) {
        this.whatsapp_number = whatsapp_number;
    }

    public void setEventAction(String event_action) {
        this.event_action = event_action;
    }
    
    
}
