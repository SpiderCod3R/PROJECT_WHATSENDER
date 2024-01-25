package whatsender.application.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import whatsender.application.logs.LogType;
import whatsender.application.helpers.MessageType;
import whatsender.application.logs.LogAnnotation;

/**
 *
 * @author ALEXANDRE
 */
@Entity
@Table(name = LogMessage.TABLE_NAME)
public class LogMessage implements Serializable, LogAnnotation  {
    public static final String TABLE_NAME= "tb_log_de_mensagem";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(columnDefinition = "TEXT", name = "mensagem")
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta dadosConsulta;
    
    @Column(name = "data")
    private String data;
    
    @Column(name = "hora")
    private String hour;
    
    @Column(name = "log_type")
    private LogType logType;
    
    @Column(name = "message_type")
    private MessageType messageType;
    
    public LogMessage() { }
    
    public LogMessage(Integer id, String message, Consulta dadosConsulta, LogType logType, MessageType messageType) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String currentDateFormated = dateFormat.format(currentDate);
        String currentHourFormated = hourFormat.format(currentDate);
        
        this.id = id;
        this.message = message;
        this.dadosConsulta = dadosConsulta;
        this.data = currentDateFormated;
        this.hour = currentHourFormated;
        this.logType = logType;
        this.messageType = messageType;
    }

    public Consulta getDadosConsulta() {
        return dadosConsulta;
    }

    public void setConsultaContato(Consulta dadosConsulta) {
        this.dadosConsulta = dadosConsulta;
    }

    @Override
    public LogType getLogType() {
        return logType;
    }
    
    @Override
    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    
    @Override
    public String getMessageLog() {
        return message;
    }

    @Override
    public void setMessageLog(String message) {
        this.message = message;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getHour() {
        return hour;
    }

    @Override
    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }  
}
