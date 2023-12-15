package whatsender.application.helpers;

/**
 *
 * @author ALEXANDRE
 */
public enum MessageType {
    NORMAL("normal"),
    LOTE("info");
    
    private String value;

    MessageType(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
