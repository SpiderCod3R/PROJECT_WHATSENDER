package bot.application.config.logs;

/**
 *
 * @author ALEXANDRE
 */
public interface LogType {
    Integer getLogId();
    void setLogId(Integer value);
    
    java.lang.String getLogType();
    void setLogType(java.lang.String value);
    
    java.lang.String getMessageLog();
    void setMessageLog(java.lang.String value);
    
    java.lang.String getData();
    void setData(java.lang.String value);
    
    java.lang.String getHour();
    void setHour(java.lang.String value); 
}
