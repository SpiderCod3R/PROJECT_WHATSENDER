package whatsender.application.logs;

import whatsender.application.helpers.MessageType;

/**
 *
 * @author ALEXANDRE
 */
public interface LogAnnotation {
    Integer getId();
    void setId(Integer value);
    
    LogType getLogType();
    void setLogType(LogType logType);
    
    String getMessageLog();
    void setMessageLog(String value);
    
    MessageType getMessageType();
    void setMessageType(MessageType messageType);
    
    String getData();
    void setData(String value);
    
    java.lang.String getHour();
    void setHour(String value); 
}
